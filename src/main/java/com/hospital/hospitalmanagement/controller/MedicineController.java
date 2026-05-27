package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Medicine;
import com.hospital.hospitalmanagement.repository.MedicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class MedicineController {

    @Autowired

    private MedicineRepository medicineRepository;

    /* ADD MEDICINE */

    @PostMapping("/addMedicine")

    public String addMedicine(
            @RequestBody Medicine medicine) {

        medicineRepository.save(medicine);

        return "Medicine Added Successfully";

    }

    /* GET ALL MEDICINES */

    @GetMapping("/medicines")

    public List<Medicine> getAllMedicines() {

        return medicineRepository.findAll();

    }

    /* DISPENSE MEDICINE */

    @PutMapping("/dispenseMedicine/{id}")

    public String dispenseMedicine(
            @PathVariable Long id) {

        Optional<Medicine> optionalMedicine =
                medicineRepository.findById(id);

        if(optionalMedicine.isPresent()) {

            Medicine medicine =
                    optionalMedicine.get();

            if(medicine.getStockQuantity() > 0) {

                medicine.setStockQuantity(
                        medicine.getStockQuantity() - 1
                );

                medicineRepository.save(medicine);

                return "Medicine Dispensed";

            }

            return "Out Of Stock";

        }

        return "Medicine Not Found";

    }

}