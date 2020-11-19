package com.example.cutikaryawan.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.UserLeaveRequest;

@Repository
public interface UserLeaveRequestRepository extends JpaRepository<UserLeaveRequest, Long>{

	// GET ALL WITH PAGINATION
	@Query(value = "SELECT * FROM user_leave_request ORDER BY user_leave_request_id",
			countQuery = "SELECT count(*) FROM user_leave_request",
			nativeQuery = true)
	Page<UserLeaveRequest> findAllRequestByIdUser(Pageable pageable);
	
}
