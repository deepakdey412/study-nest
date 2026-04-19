package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.TutorRepository;
import com.deepak.Study_Nest.dto.ApprovalRequestDto;
import com.deepak.Study_Nest.dto.TutorApprovalDto;
import com.deepak.Study_Nest.entity.Tutor;
import com.deepak.Study_Nest.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuperAdminService {

    private final TutorRepository tutorRepository;

    public List<TutorApprovalDto> getAllTutors() {
        log.info("Fetching all tutors for approval");
        return tutorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TutorApprovalDto> getPendingTutors() {
        log.info("Fetching pending tutors");
        return tutorRepository.findAll().stream()
                .filter(tutor -> "PENDING".equals(tutor.getApprovalStatus()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TutorApprovalDto approveTutor(ApprovalRequestDto request) {
        log.info("Processing approval request for tutor ID: {}", request.getTutorId());
        
        Tutor tutor = tutorRepository.findById(request.getTutorId())
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found with ID: " + request.getTutorId()));

        if ("APPROVED".equalsIgnoreCase(request.getStatus())) {
            tutor.setApprovalStatus("APPROVED");
            log.info("Tutor approved: {}", tutor.getEmail());
        } else if ("REJECTED".equalsIgnoreCase(request.getStatus())) {
            tutor.setApprovalStatus("REJECTED");
            log.info("Tutor rejected: {}", tutor.getEmail());
        } else {
            throw new IllegalArgumentException("Invalid status. Must be APPROVED or REJECTED");
        }

        tutor = tutorRepository.save(tutor);
        return convertToDto(tutor);
    }

    private TutorApprovalDto convertToDto(Tutor tutor) {
        return TutorApprovalDto.builder()
                .id(tutor.getId())
                .name(tutor.getName())
                .email(tutor.getEmail())
                .mobileNo(tutor.getMobileNo())
                .approvalStatus(tutor.getApprovalStatus())
                .createdAt(tutor.getCreatedAt())
                .build();
    }
}
