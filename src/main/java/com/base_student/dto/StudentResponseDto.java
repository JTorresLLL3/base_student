package com.base_student.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student response data")
public record StudentResponseDto(
        @Schema(description = "Student identifier", example = "1") Integer id,
        @Schema(description = "Student first name", example = "John") String name,
        @Schema(description = "Student last name", example = "Doe") String lastName,
        @Schema(description = "Student phone number", example = "+1 555 123 4567") String phone,
        @Schema(description = "Student email address", example = "john.doe@example.com") String eMail) {

}
