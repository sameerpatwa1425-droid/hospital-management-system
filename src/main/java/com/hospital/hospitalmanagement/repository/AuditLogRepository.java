package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.model.AuditLog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {

}
