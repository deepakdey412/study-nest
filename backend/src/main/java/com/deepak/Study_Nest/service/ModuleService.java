package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.dao.ModuleRepository;
import com.deepak.Study_Nest.dto.ModuleDto;
import com.deepak.Study_Nest.entity.Module;
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
public class ModuleService {

    private final ModuleRepository moduleRepository;

    @Transactional(readOnly = true)
    public List<ModuleDto> getAllModules() {
        return moduleRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModuleDto> getModulesBySemester(Integer semester) {
        return moduleRepository.findBySemester(semester).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ModuleDto getModuleById(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + id));
        return convertToDto(module);
    }

    @Transactional
    public ModuleDto createModule(ModuleDto moduleDto) {
        log.info("Creating new module: {}", moduleDto.getName());
        
        Module module = Module.builder()
                .name(moduleDto.getName())
                .code(moduleDto.getCode())
                .semester(moduleDto.getSemester())
                .description(moduleDto.getDescription())
                .imageUrl(moduleDto.getImageUrl())
                .pdfUrl(moduleDto.getPdfUrl())
                .videoLink(moduleDto.getVideoLink())
                .externalLinks(moduleDto.getExternalLinks())
                .build();
        
        Module savedModule = moduleRepository.save(module);
        log.info("Module created successfully with id: {}", savedModule.getId());
        
        return convertToDto(savedModule);
    }

    @Transactional
    public ModuleDto updateModule(Long id, ModuleDto moduleDto) {
        log.info("Updating module with id: {}", id);
        
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + id));
        
        module.setName(moduleDto.getName());
        module.setCode(moduleDto.getCode());
        module.setSemester(moduleDto.getSemester());
        module.setDescription(moduleDto.getDescription());
        module.setImageUrl(moduleDto.getImageUrl());
        module.setPdfUrl(moduleDto.getPdfUrl());
        module.setVideoLink(moduleDto.getVideoLink());
        module.setExternalLinks(moduleDto.getExternalLinks());
        
        Module updatedModule = moduleRepository.save(module);
        log.info("Module updated successfully: {}", updatedModule.getName());
        
        return convertToDto(updatedModule);
    }

    @Transactional
    public void deleteModule(Long id) {
        log.info("Deleting module with id: {}", id);
        
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + id));
        
        moduleRepository.delete(module);
        log.info("Module deleted successfully: {}", module.getName());
    }

    private ModuleDto convertToDto(Module module) {
        return ModuleDto.builder()
                .id(module.getId())
                .name(module.getName())
                .code(module.getCode())
                .semester(module.getSemester())
                .description(module.getDescription())
                .imageUrl(module.getImageUrl())
                .pdfUrl(module.getPdfUrl())
                .videoLink(module.getVideoLink())
                .externalLinks(module.getExternalLinks())
                .questionCount(module.getQuestions().size())
                .build();
    }
}
