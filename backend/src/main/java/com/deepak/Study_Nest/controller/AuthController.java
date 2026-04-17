package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.AuthResponseDto;
import com.deepak.Study_Nest.dto.LoginDto;
import com.deepak.Study_Nest.dto.StudentRegisterDto;
import com.deepak.Study_Nest.dto.TutorRegisterDto;
import com.deepak.Study_Nest.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints for students and tutors")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/student/register")
    @Operation(summary = "Register a new student")
    public ResponseEntity<AuthResponseDto> registerStudent(@Valid @RequestBody StudentRegisterDto dto) {
        AuthResponseDto response = authService.registerStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/tutor/register")
    @Operation(summary = "Register a new tutor")
    public ResponseEntity<AuthResponseDto> registerTutor(@Valid @RequestBody TutorRegisterDto dto) {
        AuthResponseDto response = authService.registerTutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login for both students and tutors")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto dto) {
        AuthResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
