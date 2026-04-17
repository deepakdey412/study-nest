package com.deepak.Study_Nest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Study_Nest.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping("/generate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CertificateService.CertificateData> generateCertificate(Authentication authentication) {
        String email = authentication.getName();
        CertificateService.CertificateData certificate = certificateService.generateCertificate(email);
        return ResponseEntity.ok(certificate);
    }
}
