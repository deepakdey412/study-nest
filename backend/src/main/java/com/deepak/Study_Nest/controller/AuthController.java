package com.deepak.Study_Nest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Study_Nest.dto.AuthResponseDto;
import com.deepak.Study_Nest.dto.LoginDto;
import com.deepak.Study_Nest.dto.StudentRegisterDto;
import com.deepak.Study_Nest.dto.TutorRegisterDto;
import com.deepak.Study_Nest.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/student/register")
    public ResponseEntity<AuthResponseDto> registerStudent(@Valid @RequestBody StudentRegisterDto dto) {
        AuthResponseDto response = authService.registerStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/tutor/register")
    public ResponseEntity<AuthResponseDto> registerTutor(@Valid @RequestBody TutorRegisterDto dto) {
        AuthResponseDto response = authService.registerTutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto dto) {
        AuthResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}
