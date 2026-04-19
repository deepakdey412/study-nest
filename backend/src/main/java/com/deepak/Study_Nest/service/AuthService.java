package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dao.SuperAdminRepository;
import com.deepak.Study_Nest.dao.TutorRepository;
import com.deepak.Study_Nest.dto.AuthResponseDto;
import com.deepak.Study_Nest.dto.LoginDto;
import com.deepak.Study_Nest.dto.StudentRegisterDto;
import com.deepak.Study_Nest.dto.TutorRegisterDto;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.entity.SuperAdmin;
import com.deepak.Study_Nest.entity.Tutor;
import com.deepak.Study_Nest.exception.DuplicateResourceException;
import com.deepak.Study_Nest.exception.InvalidCredentialsException;
import com.deepak.Study_Nest.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final StudentRepository studentRepo;
    private final TutorRepository tutorRepo;
    private final SuperAdminRepository superAdminRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponseDto registerStudent(StudentRegisterDto dto) {
        log.info("Registering student with email: {}", dto.email());

        if (studentRepo.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if (studentRepo.existsByRollNo(dto.rollNo())) {
            throw new DuplicateResourceException("Roll number already exists");
        }
        if (studentRepo.existsByPrn(dto.prn())) {
            throw new DuplicateResourceException("PRN already exists");
        }

        Student student = Student.builder()
                .name(dto.name())
                .rollNo(dto.rollNo())
                .prn(dto.prn())
                .semester(dto.semester())
                .email(dto.email())
                .mobile(dto.mobile())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        student = studentRepo.save(student);
        String token = jwtUtil.generateToken(student.getEmail());

        log.info("Student registered successfully: {}", student.getEmail());

        return AuthResponseDto.builder()
                .token(token)
                .role("STUDENT")
                .email(student.getEmail())
                .name(student.getName())
                .build();
    }

    @Transactional
    public AuthResponseDto registerTutor(TutorRegisterDto dto) {
        log.info("Registering tutor with email: {}", dto.email());

        if (tutorRepo.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if (tutorRepo.existsByMobileNo(dto.mobileNo())) {
            throw new DuplicateResourceException("Mobile number already exists");
        }

        Tutor tutor = Tutor.builder()
                .name(dto.name())
                .mobileNo(dto.mobileNo())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        tutor = tutorRepo.save(tutor);
        String token = jwtUtil.generateToken(tutor.getEmail());

        log.info("Tutor registered successfully: {}", tutor.getEmail());

        return AuthResponseDto.builder()
                .token(token)
                .role("TUTOR")
                .email(tutor.getEmail())
                .name(tutor.getName())
                .build();
    }

    public AuthResponseDto login(LoginDto dto) {
        log.info("Login attempt for email: {}", dto.email());

        // Check super admin (by email)
        var superAdminOpt = superAdminRepo.findByEmail(dto.email());
        if (superAdminOpt.isPresent()) {
            SuperAdmin superAdmin = superAdminOpt.get();
            if (passwordEncoder.matches(dto.password(), superAdmin.getPassword())) {
                String token = jwtUtil.generateToken(superAdmin.getEmail());
                log.info("Super Admin logged in successfully: {}", superAdmin.getEmail());
                return AuthResponseDto.builder()
                        .token(token)
                        .role("SUPER_ADMIN")
                        .email(superAdmin.getEmail())
                        .name(superAdmin.getName())
                        .build();
            }
        }

        // Check student
        var studentOpt = studentRepo.findByEmail(dto.email());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            if (passwordEncoder.matches(dto.password(), student.getPassword())) {
                String token = jwtUtil.generateToken(student.getEmail());
                log.info("Student logged in successfully: {}", student.getEmail());
                return AuthResponseDto.builder()
                        .token(token)
                        .role("STUDENT")
                        .email(student.getEmail())
                        .name(student.getName())
                        .build();
            }
        }

        // Check tutor
        var tutorOpt = tutorRepo.findByEmail(dto.email());
        if (tutorOpt.isPresent()) {
            Tutor tutor = tutorOpt.get();
            if (passwordEncoder.matches(dto.password(), tutor.getPassword())) {
                // Check if tutor is approved
                if (!"APPROVED".equals(tutor.getApprovalStatus())) {
                    throw new InvalidCredentialsException("Your account is pending approval. Please wait for admin approval.");
                }
                String token = jwtUtil.generateToken(tutor.getEmail());
                log.info("Tutor logged in successfully: {}", tutor.getEmail());
                return AuthResponseDto.builder()
                        .token(token)
                        .role("TUTOR")
                        .email(tutor.getEmail())
                        .name(tutor.getName())
                        .build();
            }
        }

        log.warn("Invalid login attempt for email/username: {}", dto.email());
        throw new InvalidCredentialsException("Invalid email/username or password");
    }
}
