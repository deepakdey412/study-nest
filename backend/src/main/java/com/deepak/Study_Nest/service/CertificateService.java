package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dao.StudentResultRepository;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.entity.StudentResult;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateService {

    private final StudentRepository studentRepository;
    private final StudentResultRepository resultRepository;

    @Transactional(readOnly = true)
    public CertificateData generateCertificate(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        List<StudentResult> results = resultRepository.findByStudentId(student.getId());

        if (results.isEmpty()) {
            throw new ResourceNotFoundException("No test results found for certificate generation");
        }

        // Calculate overall statistics
        long totalTests = results.size();
        long passedTests = results.stream()
                .filter(r -> "PASS".equals(r.getStatus()))
                .count();
        
        BigDecimal averagePercentage = results.stream()
                .map(StudentResult::getPercentage)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(totalTests), 2, BigDecimal.ROUND_HALF_UP);

        // Check eligibility (at least 40% average and 80% tests passed)
        boolean eligible = averagePercentage.compareTo(BigDecimal.valueOf(40)) >= 0 
                && passedTests >= (totalTests * 0.8);

        if (!eligible) {
            throw new RuntimeException("Student not eligible for certificate. Requirements: 40% average and 80% tests passed");
        }

        String certificateNumber = generateCertificateNumber(student);
        String issueDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        log.info("Certificate generated for student: {} with number: {}", email, certificateNumber);

        return CertificateData.builder()
                .certificateNumber(certificateNumber)
                .studentName(student.getName())
                .rollNo(student.getRollNo())
                .prn(student.getPrn())
                .semester(student.getSemester())
                .totalTests(totalTests)
                .passedTests(passedTests)
                .averagePercentage(averagePercentage)
                .issueDate(issueDate)
                .build();
    }

    private String generateCertificateNumber(Student student) {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String semester = String.format("%02d", student.getSemester());
        String rollNo = student.getRollNo().replaceAll("[^0-9]", "");
        return "CERT-" + year + "-SEM" + semester + "-" + rollNo;
    }

    @lombok.Data
    @lombok.Builder
    public static class CertificateData {
        private String certificateNumber;
        private String studentName;
        private String rollNo;
        private String prn;
        private Integer semester;
        private Long totalTests;
        private Long passedTests;
        private BigDecimal averagePercentage;
        private String issueDate;
    }
}
