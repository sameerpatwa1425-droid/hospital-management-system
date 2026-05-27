package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.model.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

}