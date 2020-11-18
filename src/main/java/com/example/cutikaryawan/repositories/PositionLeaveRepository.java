package com.example.cutikaryawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.PositionLeave;

@Repository
public interface PositionLeaveRepository extends JpaRepository<PositionLeave, Long>{

}
