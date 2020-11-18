package com.example.cutikaryawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.UserLeaveRequest;

@Repository
public interface UserLeaveRequestRepository extends JpaRepository<UserLeaveRequest, Long>{

}
