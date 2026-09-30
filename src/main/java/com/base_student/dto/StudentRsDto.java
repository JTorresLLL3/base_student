package com.base_student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRsDto {
    private Integer id;
    private String name;
    private String lastName;
    private String phone;
    private String eMail;
}