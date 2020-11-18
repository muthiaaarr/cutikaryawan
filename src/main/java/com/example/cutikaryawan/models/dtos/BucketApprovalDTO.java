package com.example.cutikaryawan.models.dtos;

import java.util.Date;

public class BucketApprovalDTO {

	private long bucketApprovalId;
	private UserDTO user;
	private UserLeaveRequestDTO userLeaveRequest;
	private String submissionStatus;
	private Date dateOfFiling;
	private String applicantName;
	private String resolvedBy;
	private Date resolvedDate;
	private String resolverDecision;
	private String resolverReason;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;
	
	public BucketApprovalDTO() {
		// TODO Auto-generated constructor stub
	}

	public BucketApprovalDTO(long bucketApprovalId, UserDTO user, UserLeaveRequestDTO userLeaveRequest,
			String submissionStatus, Date dateOfFiling, String applicantName, String resolvedBy, Date resolvedDate,
			String resolverDecision, String resolverReason, String createdBy, Date createdAt, String updatedBy,
			Date updatedAt) {
		super();
		this.bucketApprovalId = bucketApprovalId;
		this.user = user;
		this.userLeaveRequest = userLeaveRequest;
		this.submissionStatus = submissionStatus;
		this.dateOfFiling = dateOfFiling;
		this.applicantName = applicantName;
		this.resolvedBy = resolvedBy;
		this.resolvedDate = resolvedDate;
		this.resolverDecision = resolverDecision;
		this.resolverReason = resolverReason;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

	public long getBucketApprovalId() {
		return bucketApprovalId;
	}

	public void setBucketApprovalId(long bucketApprovalId) {
		this.bucketApprovalId = bucketApprovalId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public UserLeaveRequestDTO getUserLeaveRequest() {
		return userLeaveRequest;
	}

	public void setUserLeaveRequest(UserLeaveRequestDTO userLeaveRequest) {
		this.userLeaveRequest = userLeaveRequest;
	}

	public String getSubmissionStatus() {
		return submissionStatus;
	}

	public void setSubmissionStatus(String submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	public Date getDateOfFiling() {
		return dateOfFiling;
	}

	public void setDateOfFiling(Date dateOfFiling) {
		this.dateOfFiling = dateOfFiling;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getResolverDecision() {
		return resolverDecision;
	}

	public void setResolverDecision(String resolverDecision) {
		this.resolverDecision = resolverDecision;
	}

	public String getResolverReason() {
		return resolverReason;
	}

	public void setResolverReason(String resolverReason) {
		this.resolverReason = resolverReason;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
