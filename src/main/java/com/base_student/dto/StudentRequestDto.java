package com.base_student.dto;

import com.base_student.model.StudentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import tools.jackson.databind.ObjectMapper;

@Schema(description = "Request data for creating or updating a student")
public record StudentRequestDto(
        @Schema(description = "Student identifier", example = "1") Integer id,
        @Schema(description = "Student first name", example = "John") String name,
        @Schema(description = "Student last name", example = "Doe") String lastName,
        @Schema(description = "Student phone number", example = "+52 614 123 4567") String phone,
        @Schema(description = "Student email address", example = "john.doe@example.com") String eMail) {

    public StudentModel toModel() {
        return new StudentModel(
                this.id,
                this.name,
                this.lastName,
                this.phone,
                this.eMail);
    }

    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }

}
