package com.base_student.dto;

import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response returned by the API")
public record ErrorMessageDto(
        @Schema(description = "Error message", example = "Resource not found") String message,
        @Schema(description = "HTTP response status") HttpStatus httpStatus,
        @Schema(description = "Time at which the error occurred, in milliseconds") Long timestamp) {
}
