package com.example.cutikaryawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.BucketApproval;

@Repository
public interface BucketApprovalRepository extends JpaRepository<BucketApproval, Long>{

}
