package com.deepak.Study_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto {
    private Long id;
    private String name;
    private Integer semester;
    private String imageUrl;
    private String pdfUrl;
    private String videoLink;
    private String externalLinks;
    private Integer questionCount;
}
