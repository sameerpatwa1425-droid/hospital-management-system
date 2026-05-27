package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Consultation;
import com.hospital.hospitalmanagement.repository.ConsultationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ConsultationController {

    @Autowired

    private ConsultationRepository consultationRepository;

    /* ADD CONSULTATION */

    @PostMapping("/addConsultation")

    public String addConsultation(
            @RequestBody Consultation consultation) {

        consultationRepository.save(consultation);

        return "Consultation Added Successfully";

    }

    /* GET ALL CONSULTATIONS */

    @GetMapping("/consultations")

    public List<Consultation> getAllConsultations() {

        return consultationRepository.findAll();

    }

}
