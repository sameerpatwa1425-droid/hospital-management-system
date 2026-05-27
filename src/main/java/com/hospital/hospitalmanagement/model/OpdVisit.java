package com.hospital.hospitalmanagement.model;

import jakarta.persistence.*;

@Entity

public class OpdVisit {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long visitId;

    /* PATIENT DETAILS */

    private int patientId;

    private String patientName;

    private String doctorName;

    private int tokenNumber;

    /* CONSULTATION STATUS */

    private String status;

    /* CONSULTATION DETAILS */

    private String diagnosis;

    private String medicines;

    private String advice;

    /* BILLING */

    private double consultationFee;

    private double depositedAmount;

    private double remainingAmount;

    /* SOFT DELETE */

    private boolean deleted = false;

    /* CONSTRUCTOR */

    public OpdVisit() {

    }

    /* GETTERS & SETTERS */

    public Long getVisitId() {

        return visitId;

    }

    public void setVisitId(Long visitId) {

        this.visitId = visitId;

    }

    public int getPatientId() {

        return patientId;

    }

    public void setPatientId(int patientId) {

        this.patientId = patientId;

    }

    public String getPatientName() {

        return patientName;

    }

    public void setPatientName(String patientName) {

        this.patientName = patientName;

    }

    public String getDoctorName() {

        return doctorName;

    }

    public void setDoctorName(String doctorName) {

        this.doctorName = doctorName;

    }

    public int getTokenNumber() {

        return tokenNumber;

    }

    public void setTokenNumber(int tokenNumber) {

        this.tokenNumber = tokenNumber;

    }

    public String getStatus() {

        return status;

    }

    public void setStatus(String status) {

        this.status = status;

    }

    /* DIAGNOSIS */

    public String getDiagnosis() {

        return diagnosis;

    }

    public void setDiagnosis(String diagnosis) {

        this.diagnosis = diagnosis;

    }

    /* MEDICINES */

    public String getMedicines() {

        return medicines;

    }

    public void setMedicines(String medicines) {

        this.medicines = medicines;

    }

    /* ADVICE */

    public String getAdvice() {

        return advice;

    }

    public void setAdvice(String advice) {

        this.advice = advice;

    }

    /* CONSULTATION FEE */

    public double getConsultationFee() {

        return consultationFee;

    }

    public void setConsultationFee(
            double consultationFee
    ) {

        this.consultationFee = consultationFee;

    }

    /* DEPOSITED AMOUNT */

    public double getDepositedAmount() {

        return depositedAmount;

    }

    public void setDepositedAmount(
            double depositedAmount
    ) {

        this.depositedAmount = depositedAmount;

    }

    /* REMAINING AMOUNT */

    public double getRemainingAmount() {

        return remainingAmount;

    }

    public void setRemainingAmount(
            double remainingAmount
    ) {

        this.remainingAmount = remainingAmount;

    }

    /* SOFT DELETE */

    public boolean isDeleted() {

        return deleted;

    }

    public void setDeleted(boolean deleted) {

        this.deleted = deleted;

    }

}