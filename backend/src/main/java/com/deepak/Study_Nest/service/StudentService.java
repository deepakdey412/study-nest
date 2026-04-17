package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dto.StudentProfileDto;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
