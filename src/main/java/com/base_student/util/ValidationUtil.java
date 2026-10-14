package com.base_student.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import com.base_student.dto.StudentDto;
import com.base_student.exception.BusinessException;
import java.util.regex.Pattern;

@Component
public class ValidationUtil {

    public void validate(StudentDto studentDto) throws BusinessException {

        if (Strings.isBlank(studentDto.getEMail())
                || !Pattern.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                        studentDto.getEMail())) {
            throw BusinessException.builder()
                    .message("El email no tiene un formato válido")
                    .build();
        }

        if (Strings.isBlank(studentDto.getName())) {
            throw BusinessException.builder()
                    .message("El nombre no debe estar vacío")
                    .build();
        }

        if (studentDto.getPeso() <= 0) {
            throw BusinessException.builder()
                    .message("El peso no debe ser negativo")
                    .build();
        }

    }

}
