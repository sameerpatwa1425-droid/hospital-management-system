package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.model.Consultation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository
        extends JpaRepository<Consultation, Long> {

}