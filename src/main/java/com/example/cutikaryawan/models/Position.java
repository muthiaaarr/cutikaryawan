package com.example.cutikaryawan.models;
// Generated Nov 18, 2020 10:06:11 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
 * Position generated by hbm2java
 */
@Entity
@Table(name = "position", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value  = {"createdAt", "updatedAt"})
public class Position implements java.io.Serializable {

	private long positionId;
	private String positionName;
	private String createdBy;
	private Date createdAt;
	private String updatedBy;
	private Date updatedAt;
	private Set<PositionLeave> positionLeaves = new HashSet<PositionLeave>(0);
	private Set<User> users = new HashSet<User>(0);

	public Position() {
	}

	public Position(long positionId, String positionName, String createdBy, Date createdAt) {
		this.positionId = positionId;
		this.positionName = positionName;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public Position(long positionId, String positionName, String createdBy, Date createdAt, String updatedBy,
			Date updatedAt, Set<PositionLeave> positionLeaves, Set<User> users) {
		this.positionId = positionId;
		this.positionName = positionName;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedBy = updatedBy;
		this.updatedAt = updatedAt;
		this.positionLeaves = positionLeaves;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_position_position_id_2_seq")
	@SequenceGenerator(name = "generator_position_position_id_2_seq", sequenceName = "position_position_id_2_seq", schema = "public", allocationSize = 1)
	@Column(name = "position_id", unique = true, nullable = false)
	public long getPositionId() {
		return this.positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	@Column(name = "position_name", nullable = false)
	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "created_by")
	@CreatedBy
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 29)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	public Set<PositionLeave> getPositionLeaves() {
		return this.positionLeaves;
	}

	public void setPositionLeaves(Set<PositionLeave> positionLeaves) {
		this.positionLeaves = positionLeaves;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
