package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.TestResultDto;
import com.deepak.Study_Nest.dto.TestSubmissionDto;
import com.deepak.Study_Nest.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@Tag(name = "Tests", description = "Test submission and results endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class TestController {

    private final TestService testService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Submit test answers")
    public ResponseEntity<TestResultDto> submitTest(
            @Valid @RequestBody TestSubmissionDto submission,
            Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(testService.submitTest(email, submission));
    }

    @GetMapping("/results")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get all test results for logged-in student")
    public ResponseEntity<List<TestResultDto>> getResults(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(testService.getStudentResults(email));
    }
}
