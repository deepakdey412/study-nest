package com.deepak.Study_Nest.controller;

import com.deepak.Study_Nest.dto.ModuleDto;
import com.deepak.Study_Nest.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
@Tag(name = "Modules", description = "Module management endpoints")
@SecurityRequirement(name = "Bearer Authentication")
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    @Operation(summary = "Get all modules")
    public ResponseEntity<List<ModuleDto>> getAllModules() {
        return ResponseEntity.ok(moduleService.getAllModules());
    }

    @GetMapping("/semester/{semester}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    @Operation(summary = "Get modules by semester")
    public ResponseEntity<List<ModuleDto>> getModulesBySemester(@PathVariable Integer semester) {
        return ResponseEntity.ok(moduleService.getModulesBySemester(semester));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TUTOR')")
    @Operation(summary = "Get module by ID")
    public ResponseEntity<ModuleDto> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleService.getModuleById(id));
    }
}
