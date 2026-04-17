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

    private ModuleDto convertToDto(Module module) {
        return ModuleDto.builder()
                .id(module.getId())
                .name(module.getName())
                .semester(module.getSemester())
                .imageUrl(module.getImageUrl())
                .pdfUrl(module.getPdfUrl())
                .videoLink(module.getVideoLink())
                .externalLinks(module.getExternalLinks())
                .questionCount(module.getQuestions().size())
                .build();
    }
}
