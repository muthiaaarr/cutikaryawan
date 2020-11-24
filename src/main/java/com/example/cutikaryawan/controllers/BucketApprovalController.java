package com.example.cutikaryawan.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cutikaryawan.exceptions.IdNotFoundException;
import com.example.cutikaryawan.models.BucketApproval;
import com.example.cutikaryawan.models.User;
import com.example.cutikaryawan.models.UserLeaveRequest;
import com.example.cutikaryawan.models.dtos.BucketApprovalDTO;
import com.example.cutikaryawan.repositories.BucketApprovalRepository;
import com.example.cutikaryawan.repositories.UserLeaveRequestRepository;
import com.example.cutikaryawan.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class BucketApprovalController {

	@Autowired
	BucketApprovalRepository bucketApprovalRepository;
	@Autowired
	UserLeaveRequestRepository leaveRequestRepository;
	@Autowired
	UserRepository userRepository;
	
	ModelMapper mapper = new ModelMapper();
	
	// API
	@PostMapping("/resolve-leave-request")
	public Map<String, String> bucketApproval(@RequestBody BucketApprovalDTO approvalDTO) {
		Map<String, String> result = new HashMap<String, String>();
		List<BucketApproval> approvalList = new ArrayList<BucketApproval>();
		
		BucketApproval approval = new BucketApproval();
		approval = mapper.map(approvalDTO, BucketApproval.class);
		approvalList.add(approval);

		long idUser = approval.getUser().getUserId();
		User userBy = userRepository.findById(idUser).orElseThrow(() -> new
				IdNotFoundException(String.format("User dengan id %s tidak dapat ditemukan", idUser)));
		
		long idRequest = approval.getUserLeaveRequest().getUserLeaveRequestId();
		UserLeaveRequest request = leaveRequestRepository.findById(idRequest).orElseThrow(() -> new
						IdNotFoundException(String.format("Pengajuan cuti dengan id %s tidak ditemukan", idRequest)));
		
		User user = userRepository.findById(request.getUser().getUserId()).get();
		
		setAttribute(approval, request, user, userBy);
		
		// ERROR SALAH TANGGAL (RESOLVED DATE > LEAVE DATE FROM)
		if (validateDate(approval.getResolvedDate(), request.getLeaveDateFrom())) {
			result.put("Message", "Kesalahan data, tanggal keputusan tidak bisa lebih awal dari pengajuan cuti");
		} 
		
		// TIDAK MEMENUHI APPROVAL REQUIREMENT
		else if (userApproval(user, userBy) == false) {
			result.put("Error", String.format("%s tidak dapat approve pengajuan cuti %s", userBy.getPosition().getPositionName(),
					user.getPosition().getPositionName()));
		}
		
		// APPROVAL SUCCESS
		else {
			result.put("Message", String.format("Permohonan dengan ID %s telah berhasil diputuskan", request.getUserLeaveRequestId()));
			syncRequestAttribute(approval, request);
			bucketApprovalRepository.save(approval);
		}
		
		return result;
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
	
	// SET ATTRIBUTE
	private void setAttribute(BucketApproval approval, UserLeaveRequest request, User user, User userBy) {
		approval.setSubmissionStatus(approval.getResolverDecision());
		approval.setDateOfFiling(request.getDateOfFiling());
		approval.setApplicantName(user.getUserName());
		approval.setResolvedBy(userBy.getUserName());
		approval.setCreatedBy("Admin");
		printAttribute(approval, request);
	}
	
	// SYSOUT ATTRIBUTE
	private void printAttribute(BucketApproval approval, UserLeaveRequest request) {
		System.out.println("request id\t" + request.getUserLeaveRequestId());
		System.out.println("applicant\t" + approval.getApplicantName());
		System.out.println("date filing\t" + approval.getDateOfFiling());
		System.out.println("request\t\t" + request.getSubmissionStatus());
		System.out.println("approval\t" + approval.getSubmissionStatus());
		System.out.println("resolved\t" + approval.getResolvedDate());
		System.out.println();
	}
	
	// USER APPROVAL REQUIREMENT
	private boolean userApproval(User user, User userBy) {
		boolean valid = false;
		
		if ((user.getPosition().getPositionName().equalsIgnoreCase("employee") && userBy.getPosition().getPositionName().equalsIgnoreCase("supervisor"))
				|| (user.getPosition().getPositionName().equalsIgnoreCase("supervisor") && userBy.getPosition().getPositionName().equalsIgnoreCase("supervisor")
						&& user.getPosition().getPositionId() != userBy.getPosition().getPositionId())
				|| (user.getPosition().getPositionName().equalsIgnoreCase("staff") && userBy.getPosition().getPositionName().equalsIgnoreCase("staff"))) {
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}
	
	// SELISIH TANGGAL
	private int selisihTanggal(Date date1, Date date2) {
		
		long diff = (date1.getTime() - date2.getTime());
		long diffDays = diff / (24*60*60*1000);
		int i = (int)diffDays + 1;
		
		return i;
	}
	
	// SET REMAINING DAYS OFF AND SUBMISSION STATUS
	private void syncRequestAttribute(BucketApproval approval, UserLeaveRequest request) {
		int selisih = selisihTanggal(request.getLeaveDateTo(), request.getLeaveDateFrom());
		
		if (approval.getSubmissionStatus().equalsIgnoreCase("rejected")) {
			request.setRemainingDaysOff(request.getRemainingDaysOff() + selisih);
		} else {
			request.getRemainingDaysOff();
		}
		request.setSubmissionStatus(approval.getSubmissionStatus());
		leaveRequestRepository.save(request);
	}

	
}
