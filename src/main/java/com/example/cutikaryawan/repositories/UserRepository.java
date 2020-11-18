package com.example.cutikaryawan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cutikaryawan.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
