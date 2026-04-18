package com.deepak.Study_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDto {
    private Long id;
    private String moduleName;
    private Integer semester;
    private Integer score;
    private Integer correctAnswers;
    private Integer totalQuestions;
    private BigDecimal percentage;
    private String status;
    private Boolean passed;
    private LocalDateTime completedAt;
}
