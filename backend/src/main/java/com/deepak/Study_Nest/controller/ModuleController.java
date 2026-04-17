package com.deepak.Study_Nest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Study_Nest.dto.ModuleDto;
import com.deepak.Study_Nest.service.ModuleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    public ResponseEntity<List<ModuleDto>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    @GetMapping("/semester/{semester}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    public ResponseEntity<List<ModuleDto>> getModulesBySemester(@PathVariable Integer semester) {
        return ResponseEntity.ok(moduleService.getModulesBySemester(semester));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    public ResponseEntity<ModuleDto> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }
}
