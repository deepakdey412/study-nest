package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.StudentProfileDto;
import com.deepak.Study_Nest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentProfileDto> getProfile(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(studentService.getStudentProfile(email));
    }
}
