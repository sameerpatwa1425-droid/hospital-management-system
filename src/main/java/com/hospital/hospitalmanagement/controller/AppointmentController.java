package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Appointment;
import com.hospital.hospitalmanagement.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AppointmentController {

    @Autowired

    private AppointmentRepository appointmentRepository;

    @PostMapping("/addAppointment")

    public String addAppointment(
            @RequestBody Appointment appointment) {

        appointmentRepository.save(appointment);

        return "Appointment Booked Successfully";

    }

    @GetMapping("/appointments")

    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();

    }

    @DeleteMapping("/deleteAppointment/{id}")

    public String deleteAppointment(
            @PathVariable Long id) {

        appointmentRepository.deleteById(id);

        return "Appointment Deleted Successfully";

    }

}