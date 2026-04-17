package com.deepak.Study_Nest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDto {
    private Long id;
    private String name;
    private String rollNo;
    private String prn;
    private Integer semester;
    private String email;
    private String mobile;
}
