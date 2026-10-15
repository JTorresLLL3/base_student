package com.base_student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.base_student.dto.ErrorMessageDto;;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BusinessException businessException) {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto(
                HttpStatus.BAD_REQUEST.toString(),
                businessException.getMessage());
        return new ResponseEntity<>(errorMessageDto, HttpStatus.BAD_REQUEST);
    }

}
