package com.example.cutikaryawan.models.dtos;

import java.util.Date;

public class PositionDTO {

	private long positionId;
	private String positionName;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;
	
	public PositionDTO() {
		// TODO Auto-generated constructor stub
	}

	public PositionDTO(long positionId, String positionName, String createdBy, Date createdAt, String updatedBy,
			Date updatedAt) {
		super();
		this.positionId = positionId;
		this.positionName = positionName;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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
