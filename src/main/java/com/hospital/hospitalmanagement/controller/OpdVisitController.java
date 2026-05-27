package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.AuditLog;
import com.hospital.hospitalmanagement.model.OpdVisit;

import com.hospital.hospitalmanagement.repository.AuditLogRepository;
import com.hospital.hospitalmanagement.repository.OpdVisitRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController

public class OpdVisitController {

    @Autowired

    private OpdVisitRepository opdVisitRepository;

    @Autowired

    private AuditLogRepository auditLogRepository;

    /* ADD OPD VISIT */

    @PostMapping("/addOpdVisit")

    public String addVisit(
            @RequestBody OpdVisit opdVisit
    ) {

        opdVisitRepository.save(opdVisit);

        /* AUDIT LOG */

        AuditLog auditLog =
                new AuditLog();

        auditLog.setUserRole("RECEPTION");

        auditLog.setAction(
                "ADD_OPD_VISIT"
        );

        auditLog.setModuleName(
                "OPD"
        );

        auditLog.setDetails(
                "Patient Added To OPD Queue: "
                + opdVisit.getPatientName()
        );

        auditLog.setTimestamp(
                LocalDateTime.now().toString()
        );

        auditLogRepository.save(auditLog);

        return "OPD Visit Added Successfully";

    }

    /* GET ACTIVE VISITS */

    @GetMapping("/opdVisits")

    public List<OpdVisit> getAllVisits() {

        List<OpdVisit> allVisits =
                opdVisitRepository.findAll();

        List<OpdVisit> activeVisits =
                new ArrayList<>();

        for(OpdVisit visit : allVisits) {

            if(!visit.isDeleted()) {

                activeVisits.add(visit);

            }

        }

        return activeVisits;

    }

    /* GET DELETED VISITS */

    @GetMapping("/deletedVisits")

    public List<OpdVisit> getDeletedVisits() {

        List<OpdVisit> allVisits =
                opdVisitRepository.findAll();

        List<OpdVisit> deletedVisits =
                new ArrayList<>();

        for(OpdVisit visit : allVisits) {

            if(visit.isDeleted()) {

                deletedVisits.add(visit);

            }

        }

        return deletedVisits;

    }

    /* SOFT DELETE */

    @DeleteMapping("/deleteOpdVisit/{id}")

    public String deleteVisit(
            @PathVariable Long id
    ) {

        Optional<OpdVisit> optionalVisit =
                opdVisitRepository.findById(id);

        if(optionalVisit.isPresent()) {

            OpdVisit visit =
                    optionalVisit.get();

            visit.setDeleted(true);

            opdVisitRepository.save(visit);

            /* AUDIT LOG */

            AuditLog auditLog =
                    new AuditLog();

            auditLog.setUserRole("RECEPTION");

            auditLog.setAction(
                    "SOFT_DELETE_OPD_VISIT"
            );

            auditLog.setModuleName(
                    "OPD"
            );

            auditLog.setDetails(
                    "Soft Deleted OPD Visit For Patient: "
                    + visit.getPatientName()
            );

            auditLog.setTimestamp(
                    LocalDateTime.now().toString()
            );

            auditLogRepository.save(auditLog);

            return "OPD Visit Soft Deleted";

        }

        return "Visit Not Found";

    }

    /* RESTORE VISIT */

    @PutMapping("/restoreVisit/{id}")

    public String restoreVisit(
            @PathVariable Long id
    ) {

        Optional<OpdVisit> optionalVisit =
                opdVisitRepository.findById(id);

        if(optionalVisit.isPresent()) {

            OpdVisit visit =
                    optionalVisit.get();

            visit.setDeleted(false);

            opdVisitRepository.save(visit);

            /* AUDIT LOG */

            AuditLog auditLog =
                    new AuditLog();

            auditLog.setUserRole("ADMIN");

            auditLog.setAction(
                    "RESTORE_OPD_VISIT"
            );

            auditLog.setModuleName(
                    "RECOVERY"
            );

            auditLog.setDetails(
                    "Restored OPD Visit For Patient: "
                    + visit.getPatientName()
            );

            auditLog.setTimestamp(
                    LocalDateTime.now().toString()
            );

            auditLogRepository.save(auditLog);

            return "Visit Restored Successfully";

        }

        return "Visit Not Found";

    }

    /* SAVE CONSULTATION */

    @PutMapping("/saveConsultation/{id}")

    public String saveConsultation(

            @PathVariable Long id,

            @RequestBody Map<String, String> consultationData

    ) {

        Optional<OpdVisit> optionalVisit =
                opdVisitRepository.findById(id);

        if(optionalVisit.isPresent()) {

            OpdVisit visit =
                    optionalVisit.get();

            visit.setDiagnosis(
                    consultationData.get("diagnosis")
            );

            visit.setMedicines(
                    consultationData.get("medicines")
            );

            visit.setAdvice(
                    consultationData.get("advice")
            );

            visit.setStatus("Completed");

            opdVisitRepository.save(visit);

            /* AUDIT LOG */

            AuditLog auditLog =
                    new AuditLog();

            auditLog.setUserRole("DOCTOR");

            auditLog.setAction(
                    "SAVE_CONSULTATION"
            );

            auditLog.setModuleName(
                    "CONSULTATION"
            );

            auditLog.setDetails(
                    "Consultation Saved For Patient: "
                    + visit.getPatientName()
            );

            auditLog.setTimestamp(
                    LocalDateTime.now().toString()
            );

            auditLogRepository.save(auditLog);

            return "Consultation Saved Successfully";

        }

        return "Visit Not Found";

    }

    /* PAYMENT UPDATE */

    @PutMapping("/payBill/{id}/{amount}")

    public String payBill(

            @PathVariable Long id,

            @PathVariable Double amount

    ) {

        Optional<OpdVisit> optionalVisit =
                opdVisitRepository.findById(id);

        if(optionalVisit.isPresent()) {

            OpdVisit visit =
                    optionalVisit.get();

            double deposited =
                    visit.getDepositedAmount()
                    + amount;

            double remaining =
                    visit.getConsultationFee()
                    - deposited;

            visit.setDepositedAmount(
                    deposited
            );

            visit.setRemainingAmount(
                    Math.max(remaining, 0)
            );

            opdVisitRepository.save(visit);

            /* AUDIT LOG */

            AuditLog auditLog =
                    new AuditLog();

            auditLog.setUserRole("RECEPTION");

            auditLog.setAction(
                    "UPDATE_PAYMENT"
            );

            auditLog.setModuleName(
                    "BILLING"
            );

            auditLog.setDetails(
                    "Payment Updated For Patient: "
                    + visit.getPatientName()
                    + " | Amount Paid: ₹"
                    + amount
            );

            auditLog.setTimestamp(
                    LocalDateTime.now().toString()
            );

            auditLogRepository.save(auditLog);

            return "Payment Updated";

        }

        return "Visit Not Found";

    }

    /* ANALYTICS DASHBOARD */

    @GetMapping("/analytics")

    public Map<String, Object> getAnalytics() {

        List<OpdVisit> visits =
                opdVisitRepository.findAll();

        int totalPatients = 0;

        int completedConsultations = 0;

        int deletedRecords = 0;

        double totalRevenue = 0;

        double pendingPayments = 0;

        for(OpdVisit visit : visits) {

            if(!visit.isDeleted()) {

                totalPatients++;

                totalRevenue +=
                        visit.getDepositedAmount();

                pendingPayments +=
                        visit.getRemainingAmount();

                if("Completed".equalsIgnoreCase(
                        visit.getStatus()
                )) {

                    completedConsultations++;

                }

            }

            else {

                deletedRecords++;

            }

        }

        return Map.of(

                "totalPatients",
                totalPatients,

                "completedConsultations",
                completedConsultations,

                "deletedRecords",
                deletedRecords,

                "totalRevenue",
                totalRevenue,

                "pendingPayments",
                pendingPayments

        );

    }

}