package com.deepak.Study_Nest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Study_Nest.service.CertificateService;
import com.deepak.Study_Nest.service.PdfCertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;
    private final PdfCertificateService pdfCertificateService;

    @GetMapping("/generate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CertificateService.CertificateData> generateCertificate(Authentication authentication) {
        String email = authentication.getName();
        CertificateService.CertificateData certificate = certificateService.generateCertificate(email);
        return ResponseEntity.ok(certificate);
    }

    @GetMapping("/eligibility")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CertificateService.EligibilityData> checkEligibility(Authentication authentication) {
        String email = authentication.getName();
        CertificateService.EligibilityData eligibility = certificateService.checkEligibility(email);
        return ResponseEntity.ok(eligibility);
    }

    @GetMapping("/download-pdf")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<byte[]> downloadCertificatePdf(Authentication authentication) {
        try {
            String email = authentication.getName();
            CertificateService.CertificateData certificate = certificateService.generateCertificate(email);
            byte[] pdfBytes = pdfCertificateService.generateCertificatePdf(certificate);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", 
                    "StudyNest_Certificate_" + certificate.getStudentName().replace(" ", "_") + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
