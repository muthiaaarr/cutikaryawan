package com.example.cutikaryawan.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.UserLeaveRequest;

@Repository
public interface UserLeaveRequestRepository extends JpaRepository<UserLeaveRequest, Long>/*,
				PagingAndSortingRepository<UserLeaveRequest, Long>*/ {

	// GET ALL WITH PAGINATION
	/*@Query(value = "SELECT user_leave_request.user_id, user_leave_request.leave_date_from, "
			+ "user_leave_request.leave_date_to, user_leave_request.description, user_leave_request.submission_status, "
			+ "bucket_approval.resolver_reason, bucket_approval.resolved_by, bucket_approval.resolved_date "
			+ "FROM user_leave_request "
			+ "LEFT JOIN bucket_approval ON user_leave_request.user_leave_request_id = bucket_approval.user_leave_request_id "
			+ "WHERE user_leave_request.user_id =?1 "
			+ "ORDER BY user_leave_request.user_leave_request_id",
			countQuery = "SELECT count(*) FROM user_leave_request",
			nativeQuery = true)
	Page<UserLeaveRequest> findAllRequestByIdUser(Pageable pageable, Long id);*/
	
	@Query(value = "SELECT * FROM user_leave_request "
			+ "WHERE user_leave_request.user_id =?1 "
			+ "ORDER BY user_leave_request.user_leave_request_id",
			countQuery = "SELECT count(*) FROM user_leave_request",
			nativeQuery = true)
	Page<UserLeaveRequest> findAllRequestByIdUser(Pageable pageable, Long id);
	
	// GET LIST REQUEST BY ID AND DATE
	@Query(value = "SELECT * FROM user_leave_request "
			+ "WHERE user_leave_request.user_id =?1 "
			+ "AND user_leave_request.leave_date_from =?2 "
			+ "ORDER BY user_leave_request.user_leave_request_id",
			countQuery = "SELECT count(*) FROM user_leave_request",
			nativeQuery = true)
	UserLeaveRequest findRequestByDate(Long id, Date date);
	
}
