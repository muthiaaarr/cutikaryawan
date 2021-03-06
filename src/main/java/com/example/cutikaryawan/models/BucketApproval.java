package com.example.cutikaryawan.models;
// Generated Nov 18, 2020 10:06:11 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * BucketApproval generated by hbm2java
 */
@Entity
@Table(name = "bucket_approval", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"})
public class BucketApproval implements java.io.Serializable {

	private long bucketApprovalId;
	private User user;
	private UserLeaveRequest userLeaveRequest;
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

	public BucketApproval() {
	}

	public BucketApproval(long bucketApprovalId, String resolvedBy, Date resolvedDate, String resolverDecision,
			String resolverReason, String createdBy, Date createdAt) {
		this.bucketApprovalId = bucketApprovalId;
		this.resolvedBy = resolvedBy;
		this.resolvedDate = resolvedDate;
		this.resolverDecision = resolverDecision;
		this.resolverReason = resolverReason;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}

	public BucketApproval(long bucketApprovalId, User user, UserLeaveRequest userLeaveRequest, String submissionStatus,
			Date dateOfFiling, String applicantName, String resolvedBy, Date resolvedDate, String resolverDecision,
			String resolverReason, String createdBy, Date createdAt, String updatedBy, Date updatedAt) {
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_bucket_approval_bucket_approval_id_2_seq")
	@SequenceGenerator(name = "generator_bucket_approval_bucket_approval_id_2_seq", sequenceName = "bucket_approval_bucket_approval_id_2_seq",
							schema = "public", allocationSize = 1)
	@Column(name = "bucket_approval_id", unique = true, nullable = false)
	public long getBucketApprovalId() {
		return this.bucketApprovalId;
	}

	public void setBucketApprovalId(long bucketApprovalId) {
		this.bucketApprovalId = bucketApprovalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_leave_request_id")
	public UserLeaveRequest getUserLeaveRequest() {
		return this.userLeaveRequest;
	}

	public void setUserLeaveRequest(UserLeaveRequest userLeaveRequest) {
		this.userLeaveRequest = userLeaveRequest;
	}

	@Column(name = "submission_status")
	public String getSubmissionStatus() {
		return this.submissionStatus;
	}

	public void setSubmissionStatus(String submissionStatus) {
		this.submissionStatus = submissionStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_filing", length = 13)
	public Date getDateOfFiling() {
		return this.dateOfFiling;
	}

	public void setDateOfFiling(Date dateOfFiling) {
		this.dateOfFiling = dateOfFiling;
	}

	@Column(name = "applicant_name")
	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Column(name = "resolved_by", nullable = false)
	public String getResolvedBy() {
		return this.resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "resolved_date", nullable = false, length = 13)
	public Date getResolvedDate() {
		return this.resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	@Column(name = "resolver_decision", nullable = false)
	public String getResolverDecision() {
		return this.resolverDecision;
	}

	public void setResolverDecision(String resolverDecision) {
		this.resolverDecision = resolverDecision;
	}

	@Column(name = "resolver_reason", nullable = false)
	public String getResolverReason() {
		return this.resolverReason;
	}

	public void setResolverReason(String resolverReason) {
		this.resolverReason = resolverReason;
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

}
