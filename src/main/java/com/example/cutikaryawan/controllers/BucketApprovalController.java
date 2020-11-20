package com.example.cutikaryawan.controllers;

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
		List<BucketApproval> approvalList = bucketApprovalRepository.findAll();
		
		BucketApproval approval = new BucketApproval();
		approval = mapper.map(approvalDTO, BucketApproval.class);
		approvalList.add(approval);
		
		UserLeaveRequest request = leaveRequestRepository.findById(
				approval.getUserLeaveRequest().getUserLeaveRequestId()).get();
		User user = userRepository.findById(request.getUser().getUserId()).get();
		
		approval.setSubmissionStatus(approval.getResolverDecision());
		setRequestStatus(request);
		approval.setDateOfFiling(request.getDateOfFiling());
		approval.setApplicantName(user.getUserName());
		approval.setCreatedBy("Muthia");
		
		for (BucketApproval b : approvalList) {
			if (validateDate(b.getResolvedDate(), request.getLeaveDateFrom())) {
				result.put("Message", "Kesalahan data, tanggal keputusan tidak bisa lebih awal dari pengajuan cuti");
			} else {
				result.put("Message", String.format("Permohonan dengan ID %s telah berhasil diputuskan", request.getUserLeaveRequestId()));
				bucketApprovalRepository.save(approval);
			}
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
	
	// SET SUBMISSION STATUS
	private void setRequestStatus(UserLeaveRequest leaveRequest) {
		List<BucketApproval> approvalList = bucketApprovalRepository.findAll();
		
		for (BucketApproval b : approvalList) {
			UserLeaveRequest request = leaveRequestRepository.findById(b.getUserLeaveRequest().getUserLeaveRequestId()).get();
			leaveRequest.setSubmissionStatus(b.getResolverDecision());
			leaveRequestRepository.save(request);
		}
	}

	
}
