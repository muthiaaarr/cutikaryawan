package com.example.cutikaryawan.models.dtos;

import java.util.Date;

public class PositionLeaveDTO {

	private long positionLeaveId;
	private PositionDTO position;
	private int jatahCuti;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;
	
	public PositionLeaveDTO() {
		// TODO Auto-generated constructor stub
	}

	public PositionLeaveDTO(long positionLeaveId, PositionDTO position, int jatahCuti, String createdBy, Date createdAt,
			String updatedBy, Date updatedAt) {
		super();
		this.positionLeaveId = positionLeaveId;
		this.position = position;
		this.jatahCuti = jatahCuti;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

	public long getPositionLeaveId() {
		return positionLeaveId;
	}

	public void setPositionLeaveId(long positionLeaveId) {
		this.positionLeaveId = positionLeaveId;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public int getJatahCuti() {
		return jatahCuti;
	}

	public void setJatahCuti(int jatahCuti) {
		this.jatahCuti = jatahCuti;
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
