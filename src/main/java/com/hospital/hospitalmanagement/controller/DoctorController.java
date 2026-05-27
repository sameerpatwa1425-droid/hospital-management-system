package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class DoctorController {

    @Autowired

    private DoctorRepository doctorRepository;

    @PostMapping("/addDoctor")

    public String addDoctor(@RequestBody Doctor doctor) {

        doctorRepository.save(doctor);

        return "Doctor Added Successfully";

    }

    @GetMapping("/doctors")

    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();

    }

    @DeleteMapping("/deleteDoctor/{id}")

    public String deleteDoctor(@PathVariable Long id) {

        doctorRepository.deleteById(id);

        return "Doctor Deleted Successfully";

    }

}