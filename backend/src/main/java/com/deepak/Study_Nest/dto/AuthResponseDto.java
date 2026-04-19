package com.deepak.Study_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String role;
    private String email;
    private String name;
    private String approvalStatus; // For tutors: PENDING, APPROVED, REJECTED
}
