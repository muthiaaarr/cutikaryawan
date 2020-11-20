package com.example.cutikaryawan.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cutikaryawan.exceptions.DateNotFoundException;
import com.example.cutikaryawan.models.BucketApproval;
import com.example.cutikaryawan.models.PositionLeave;
import com.example.cutikaryawan.models.User;
import com.example.cutikaryawan.models.UserLeaveRequest;
import com.example.cutikaryawan.models.dtos.UserLeaveRequestDTO;
import com.example.cutikaryawan.repositories.BucketApprovalRepository;
import com.example.cutikaryawan.repositories.PositionLeaveRepository;
import com.example.cutikaryawan.repositories.UserLeaveRequestRepository;
import com.example.cutikaryawan.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class RequestLeaveController {

	@Autowired
	UserLeaveRequestRepository leaveRequestRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PositionLeaveRepository positionLeaveRepository;
	@Autowired
	BucketApprovalRepository approvalRepository;
	
	ModelMapper mapper = new ModelMapper();
	SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy"); 
	
	// API REQUEST LEAVE
	@PostMapping("/leave-request")
	public Map<String, String> requestLeave(@RequestBody UserLeaveRequestDTO leaveRequestDTO) {
		Map<String, String> result = new HashMap<>();
		List<UserLeaveRequest> requestList = new ArrayList<UserLeaveRequest>();
		
		UserLeaveRequest leaveRequest = new UserLeaveRequest();		
		leaveRequest = mapper.map(leaveRequestDTO, UserLeaveRequest.class);
		requestList.add(leaveRequest);
		
		User user = userRepository.findById(leaveRequest.getUser().getUserId()).get();
		leaveRequest.setRemainingDaysOff(calculateRemainingDaysOff(leaveRequest, user));
		jatahCutiMax(user);
		
		for (UserLeaveRequest leaveRequest2 : requestList) {
					
			// JATAH CUTI SESUAI
			if (validateJatahCuti(leaveRequest2, user)) {
				result.put("Message", "Permohonan Anda sedang diproses");
				leaveRequest2.setSubmissionStatus("waiting");
				leaveRequest2.setCreatedBy("Muthia");
				leaveRequestRepository.save(leaveRequest2);
			} 
			
			// ERROR JATAH CUTI HABIS
			else if (leaveRequest2.getRemainingDaysOff() == 0) {
				result.put("Message", "Mohon maaf, jatah cuti Anda habis");
			}
			
			// ERROR JATAH CUTI TIDAK CUKUP
			else if (validateJatahCuti(leaveRequest2, user) == false) {
				String dateFrom = date.format(leaveRequest2.getLeaveDateFrom());
				String dateTo = date.format(leaveRequest2.getLeaveDateTo());
				result.put("Message", String.format("Mohon maaf, jatah cuti Anda tidak cukup untuk digunakan dari tanggal %s"
						+ " sampai %s (%s hari). Jatah cuti Anda yang tersisa adalah %s hari", dateFrom, dateTo,
						selisihTanggal(leaveRequest2.getLeaveDateTo(), leaveRequest2.getLeaveDateFrom()),
						(-leaveRequest2.getRemainingDaysOff())));
			}
			
			// ERROR SALAH TANGGAL (LEAVE DATE FROM > LEAVE DATE TO) 
			else if (validateDate(leaveRequest2.getLeaveDateFrom(), leaveRequest2.getLeaveDateTo())) {
				result.put("Message", "Tanggal yang Anda ajukan tidak valid");
			} 
			
			// ERROR CUTI BACKDATE (TANGGAL PENGAJUAN CUTI < TANGGAL HARI INI)
			else if (validateDate(leaveRequest2.getDateOfFiling(), leaveRequest2.getLeaveDateFrom())) {
				result.put("Message", "Tanggal yang Anda ajukan telah lampau, silakan ganti tanggal pengajuan cuti Anda");
			}
		}		
		
		return result;
	}
	
	// API GET ALL LEAVE REQUEST LIST
	@GetMapping("/leave-request-list/{userId}/{totalDataPerPage}/{choosenPage}")
	public List<UserLeaveRequestDTO> requestList(@PathVariable Long userId, @PathVariable int totalDataPerPage,
			@PathVariable int choosenPage) {
		
		Pageable paging = PageRequest.of(choosenPage, totalDataPerPage);
		Page<UserLeaveRequest> result = leaveRequestRepository.findAllRequestByIdUser(paging, userId);
		
		List<UserLeaveRequestDTO> listDTO = new ArrayList<>();
		
		for (UserLeaveRequest u : result) {
			UserLeaveRequestDTO requestDTO = mapper.map(u, UserLeaveRequestDTO.class);
			listDTO.add(requestDTO);
		}
		
		return listDTO;
	}
	
	// GET REQUEST BY ID AND DATE
	@GetMapping("/leave-request-list/{userId}/{date}")
	public Map<String, Object> requestByDate(@PathVariable Long userId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		UserLeaveRequest data = leaveRequestRepository.findRequestByDate(userId, date);
		UserLeaveRequestDTO dataDTO = new UserLeaveRequestDTO();
		dataDTO = mapper.map(data, UserLeaveRequestDTO.class);
		
		/*if (data != null) {
			throw new DateNotFoundException(String.format("Pengajuan tanggal %s untuk user %s tidak ditemukan", date, userId));
		} else {
			dataDTO = mapper.map(data, UserLeaveRequestDTO.class);
		}*/
		
		result.put("Message", "Data found!");
		result.put("Data", dataDTO);
		
		return result;
	}
	
	// VALIDATE POSITION LEAVE
	private int jatahCutiMax(User user) {
		int jatahCuti = 0;
		List<PositionLeave> positionLeave = positionLeaveRepository.findAll();
		
		for (PositionLeave p : positionLeave) {
			if (p.getPosition().getPositionId() == user.getPosition().getPositionId()) {	
				jatahCuti = p.getJatahCuti();
			} else {
				return jatahCuti;
			}
		}	
		
		return jatahCuti;
	}
	
	// VALIDATE 2 DATES
	private boolean validateDate(Date date1, Date date2) {
		boolean fixDate = false;
		
		Calendar setDate1 = Calendar.getInstance();
		setDate1.setTime(date1);
		Calendar setDate2 = Calendar.getInstance();
		setDate2.setTime(date2);
		
		if (setDate1.compareTo(setDate2) > 0) {
			fixDate = true;
		} else {
			fixDate = false;
		}
		
		return fixDate;
	}
	
	// SELISIH TANGGAL
	private int selisihTanggal(Date date1, Date date2) {
		
		long diff = (date1.getTime() - date2.getTime());
		long diffDays = diff / (24*60*60*1000);
		int i = (int)diffDays;
		
		return i;
	}
	
	// VALIDATE JATAH CUTI
	private boolean validateJatahCuti(UserLeaveRequest leaveRequest, User user) {
		boolean result = false;
		
		if (selisihTanggal(leaveRequest.getLeaveDateTo(), leaveRequest.getLeaveDateFrom()) <= jatahCutiMax(user) /*&&
				selisihTanggal(leaveRequest.getLeaveDateTo(), leaveRequest.getLeaveDateFrom()) <= 
				leaveRequest.getRemainingDaysOff()*/) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
	// VALIDATE REMAINING DAYS OFF
	private int calculateRemainingDaysOff(UserLeaveRequest leaveRequest, User user) {
		int days = jatahCutiMax(user) - selisihTanggal(leaveRequest.getLeaveDateTo(), leaveRequest.getLeaveDateFrom());
		
		return days;
	}
	


}
