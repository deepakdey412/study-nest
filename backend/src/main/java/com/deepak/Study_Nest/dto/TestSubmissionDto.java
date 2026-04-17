package com.deepak.Study_Nest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSubmissionDto {
    @NotNull(message = "Module ID is required")
    private Long moduleId;

    @NotEmpty(message = "Answers are required")
    private Map<Long, String> answers; // questionId -> selectedAnswer
}
