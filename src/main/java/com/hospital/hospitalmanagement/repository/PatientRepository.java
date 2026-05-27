package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
        extends JpaRepository<Patient, Long> {

}
