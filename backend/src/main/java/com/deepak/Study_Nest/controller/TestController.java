package com.deepak.Study_Nest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Study_Nest.dto.TestResultDto;
import com.deepak.Study_Nest.dto.TestSubmissionDto;
import com.deepak.Study_Nest.service.TestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<TestResultDto> submitTest(
            @Valid @RequestBody TestSubmissionDto submission,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(testService.submitTest(email, submission));
    }

    @GetMapping("/results")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<TestResultDto>> getResults(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(testService.getStudentResults(email));
    }
}
