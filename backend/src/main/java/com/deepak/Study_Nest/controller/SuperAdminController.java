package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.ApprovalRequestDto;
import com.deepak.Study_Nest.dto.TutorApprovalDto;
import com.deepak.Study_Nest.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superadmin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping("/tutors")
    public ResponseEntity<List<TutorApprovalDto>> getAllTutors() {
        return ResponseEntity.ok(superAdminService.getAllTutors());
    }

    @GetMapping("/tutors/pending")
    public ResponseEntity<List<TutorApprovalDto>> getPendingTutors() {
        return ResponseEntity.ok(superAdminService.getPendingTutors());
    }

    @PostMapping("/tutors/approve")
    public ResponseEntity<TutorApprovalDto> approveTutor(@RequestBody ApprovalRequestDto request) {
        return ResponseEntity.ok(superAdminService.approveTutor(request));
    }
}
