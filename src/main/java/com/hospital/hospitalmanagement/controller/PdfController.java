package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.OpdVisit;
import com.hospital.hospitalmanagement.repository.OpdVisitRepository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.util.Optional;

@RestController

public class PdfController {

    @Autowired

    private OpdVisitRepository opdVisitRepository;

    /* FULL MEDICAL REPORT */

    @GetMapping("/generateFullReport/{visitId}")

    public void generateFullReport(

            @PathVariable Long visitId,

            HttpServletResponse response

    ) {

        try {

            Optional<OpdVisit> optionalVisit =
                    opdVisitRepository.findById(visitId);

            if(optionalVisit.isEmpty()) {

                response.getWriter().write(
                        "Visit Not Found"
                );

                return;

            }

            OpdVisit visit =
                    optionalVisit.get();

            /* PDF RESPONSE */

            response.setContentType(
                    "application/pdf"
            );

            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=full_medical_report.pdf"
            );

            /* PDF DOCUMENT */

            Document document =
                    new Document();

            OutputStream outputStream =
                    response.getOutputStream();

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            /* FONTS */

            Font titleFont =
                    new Font(
                            Font.FontFamily.HELVETICA,
                            24,
                            Font.BOLD
                    );

            Font headingFont =
                    new Font(
                            Font.FontFamily.HELVETICA,
                            18,
                            Font.BOLD
                    );

            Font normalFont =
                    new Font(
                            Font.FontFamily.HELVETICA,
                            14,
                            Font.NORMAL
                    );

            /* TITLE */

            Paragraph title =
                    new Paragraph(
                            "Full Medical Report",
                            titleFont
                    );

            title.setSpacingAfter(25);

            document.add(title);

            /* PATIENT DETAILS */

            Paragraph patientHeading =
                    new Paragraph(
                            "Patient Details",
                            headingFont
                    );

            patientHeading.setSpacingAfter(10);

            document.add(patientHeading);

            document.add(
                    new Paragraph(
                            "Visit ID: "
                            + visit.getVisitId(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Patient ID: "
                            + visit.getPatientId(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Patient Name: "
                            + visit.getPatientName(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Doctor Name: "
                            + visit.getDoctorName(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            /* CONSULTATION DETAILS */

            Paragraph consultationHeading =
                    new Paragraph(
                            "Consultation Details",
                            headingFont
                    );

            consultationHeading.setSpacingAfter(10);

            document.add(consultationHeading);

            document.add(
                    new Paragraph(
                            "Diagnosis: "
                            + visit.getDiagnosis(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Medicines: "
                            + visit.getMedicines(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Advice: "
                            + visit.getAdvice(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Consultation Status: "
                            + visit.getStatus(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            /* BILLING DETAILS */

            Paragraph billingHeading =
                    new Paragraph(
                            "Billing Details",
                            headingFont
                    );

            billingHeading.setSpacingAfter(10);

            document.add(billingHeading);

            document.add(
                    new Paragraph(
                            "Consultation Fee: ₹"
                            + visit.getConsultationFee(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Deposited Amount: ₹"
                            + visit.getDepositedAmount(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(
                            "Remaining Amount: ₹"
                            + visit.getRemainingAmount(),
                            normalFont
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            /* FOOTER */

            Paragraph footer =
                    new Paragraph(
                            "Thank You For Visiting Our Hospital",
                            normalFont
                    );

            footer.setSpacingBefore(20);

            document.add(footer);

            document.close();

            outputStream.close();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

    }

}