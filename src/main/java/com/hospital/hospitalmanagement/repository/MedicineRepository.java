package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.model.Medicine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository
        extends JpaRepository<Medicine, Long> {

}
