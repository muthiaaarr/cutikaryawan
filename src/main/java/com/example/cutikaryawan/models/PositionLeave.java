package com.example.cutikaryawan.models;
// Generated Nov 18, 2020 10:06:11 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * PositionLeave generated by hbm2java
 */
@Entity
@Table(name = "position_leave", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"})
public class PositionLeave implements java.io.Serializable {

	private long positionLeaveId;
	private Position position;
	private int jatahCuti;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;

	public PositionLeave() {
	}

	public PositionLeave(long positionLeaveId, Position position, int jatahCuti, String createdBy, Date createdAt) {
		this.positionLeaveId = positionLeaveId;
		this.position = position;
		this.jatahCuti = jatahCuti;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public PositionLeave(long positionLeaveId, Position position, int jatahCuti, String createdBy, Date createdAt,
			String updatedBy, Date updatedAt) {
		this.positionLeaveId = positionLeaveId;
		this.position = position;
		this.jatahCuti = jatahCuti;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
	}

	@Id

	@Column(name = "position_leave_id", unique = true, nullable = false)
	public long getPositionLeaveId() {
		return this.positionLeaveId;
	}

	public void setPositionLeaveId(long positionLeaveId) {
		this.positionLeaveId = positionLeaveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id", nullable = false)
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Column(name = "jatah_cuti", nullable = false)
	public int getJatahCuti() {
		return this.jatahCuti;
	}

	public void setJatahCuti(int jatahCuti) {
		this.jatahCuti = jatahCuti;
	}

	@Column(name = "created_by", nullable = false)
	@CreatedBy
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 29)
	@CreatedDate
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_by")
	@LastModifiedBy
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 29)
	@LastModifiedDate
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
