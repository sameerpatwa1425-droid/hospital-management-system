package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Prescription;
import com.hospital.hospitalmanagement.repository.PrescriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PrescriptionController {

    @Autowired

    private PrescriptionRepository prescriptionRepository;

    /* ADD PRESCRIPTION */

    @PostMapping("/addPrescription")

    public String addPrescription(
            @RequestBody Prescription prescription) {

        prescriptionRepository.save(prescription);

        return "Prescription Added Successfully";

    }

    /* GET ALL PRESCRIPTIONS */

    @GetMapping("/prescriptions")

    public List<Prescription> getAllPrescriptions() {

        return prescriptionRepository.findAll();

    }

}
