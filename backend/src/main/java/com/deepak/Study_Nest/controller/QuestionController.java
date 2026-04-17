package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.QuestionDto;
import com.deepak.Study_Nest.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@Tag(name = "Questions", description = "Question management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/module/{moduleId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get questions for a module (without answers)")
    public ResponseEntity<List<QuestionDto>> getQuestionsForStudent(@PathVariable Long moduleId) {
        return ResponseEntity.ok(questionService.getQuestionsByModuleId(moduleId, false));
    }

    @GetMapping("/module/{moduleId}/with-answers")
    @PreAuthorize("hasRole('TUTOR')")
    @Operation(summary = "Get questions for a module (with answers - tutors only)")
    public ResponseEntity<List<QuestionDto>> getQuestionsForTutor(@PathVariable Long moduleId) {
        return ResponseEntity.ok(questionService.getQuestionsByModuleId(moduleId, true));
    }
}
