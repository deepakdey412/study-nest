package com.deepak.Study_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorApprovalDto {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private String approvalStatus;
    private LocalDateTime createdAt;
}
