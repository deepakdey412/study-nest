package com.deepak.Study_Nest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dto.StudentProfileDto;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public StudentProfileDto getStudentProfile(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));

        return StudentProfileDto.builder()
                .id(student.getId())
                .name(student.getName())
                .rollNo(student.getRollNo())
                .prn(student.getPrn())
                .semester(student.getSemester())
                .email(student.getEmail())
                .mobile(student.getMobile())
                .build();
    }

    @Transactional
    public StudentProfileDto updateStudentProfile(String email, StudentProfileDto profileDto) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));

        // Update only allowed fields (PRN cannot be changed)
        if (profileDto.getName() != null) {
            student.setName(profileDto.getName());
        }
        if (profileDto.getRollNo() != null) {
            student.setRollNo(profileDto.getRollNo());
        }
        if (profileDto.getEmail() != null) {
            student.setEmail(profileDto.getEmail());
        }
        if (profileDto.getMobile() != null) {
            student.setMobile(profileDto.getMobile());
        }
        if (profileDto.getSemester() != null) {
            student.setSemester(profileDto.getSemester());
        }

        Student updatedStudent = studentRepository.save(student);
        log.info("Updated profile for student: {}", email);

        return StudentProfileDto.builder()
                .id(updatedStudent.getId())
                .name(updatedStudent.getName())
                .rollNo(updatedStudent.getRollNo())
                .prn(updatedStudent.getPrn())
                .semester(updatedStudent.getSemester())
                .email(updatedStudent.getEmail())
                .mobile(updatedStudent.getMobile())
                .build();
    }
}
