package com.example.cutikaryawan.models.dtos;

import java.util.Date;

public class UserLeaveRequestDTO {

	private long userLeaveRequestId;
	private UserDTO user;
	private String submissionStatus;
	private Date dateOfFiling;
	private Date leaveDateFrom;
	private Date leaveDateTo;
	private String description;
	private Integer remainingDaysOff;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;
	
	public UserLeaveRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserLeaveRequestDTO(long userLeaveRequestId, UserDTO user, String submissionStatus, Date dateOfFiling,
			Date leaveDateFrom, Date leaveDateTo, String description, Integer remainingDaysOff, String createdBy,
			Date createdAt, String updatedBy, Date updatedAt) {
		super();
		this.userLeaveRequestId = userLeaveRequestId;
		this.user = user;
		this.submissionStatus = submissionStatus;
		this.dateOfFiling = dateOfFiling;
		this.leaveDateFrom = leaveDateFrom;
		this.leaveDateTo = leaveDateTo;
		this.description = description;
		this.remainingDaysOff = remainingDaysOff;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

	public long getUserLeaveRequestId() {
		return userLeaveRequestId;
	}

	public void setUserLeaveRequestId(long userLeaveRequestId) {
		this.userLeaveRequestId = userLeaveRequestId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	public Date getLeaveDateFrom() {
		return leaveDateFrom;
	}

	public void setLeaveDateFrom(Date leaveDateFrom) {
		this.leaveDateFrom = leaveDateFrom;
	}

	public Date getLeaveDateTo() {
		return leaveDateTo;
	}

	public void setLeaveDateTo(Date leaveDateTo) {
		this.leaveDateTo = leaveDateTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRemainingDaysOff() {
		return remainingDaysOff;
	}

	public void setRemainingDaysOff(Integer remainingDaysOff) {
		this.remainingDaysOff = remainingDaysOff;
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
