package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.AuditLog;
import com.hospital.hospitalmanagement.repository.AuditLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AuditLogController {

    @Autowired

    private AuditLogRepository auditLogRepository;

    /* ADD AUDIT LOG */

    @PostMapping("/addAuditLog")

    public String addAuditLog(
            @RequestBody AuditLog auditLog) {

        auditLogRepository.save(auditLog);

        return "Audit Log Added";

    }

    /* GET ALL LOGS */

    @GetMapping("/auditLogs")

    public List<AuditLog> getAllLogs() {

        return auditLogRepository.findAll();

    }

}
