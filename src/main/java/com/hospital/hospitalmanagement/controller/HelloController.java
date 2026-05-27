package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HelloController {

    @Autowired

    private PatientRepository patientRepository;

    @GetMapping("/hello")

    public String hello() {

        return "Hospital Management System Started Successfully";

    }

    @PostMapping("/addPatient")

    public String addPatient(@RequestBody Patient patient) {

        patientRepository.save(patient);

        return "Patient Added Successfully";

    }

    @GetMapping("/patients")

    public List<Patient> getAllPatients() {

        return patientRepository.findAll();

    }

    @DeleteMapping("/deletePatient/{id}")

    public String deletePatient(@PathVariable Long id) {

        patientRepository.deleteById(id);

        return "Patient Deleted Successfully";

    }

    @PutMapping("/updatePatient/{id}")

    public String updatePatient(@PathVariable Long id,
                                @RequestBody Patient updatedPatient) {

        Patient patient =
                patientRepository.findById(id).orElse(null);

        if (patient == null) {

            return "Patient Not Found";

        }

        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setDisease(updatedPatient.getDisease());

        patientRepository.save(patient);

        return "Patient Updated Successfully";

    }

}