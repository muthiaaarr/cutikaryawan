package com.example.cutikaryawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

}
