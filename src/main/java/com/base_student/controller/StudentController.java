package com.base_student.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base_student.util.ValidationUtil;
import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.service.StudentService;

@Controller
@RequestMapping("${api.student.url}")
@Tag(name = "Students", description = "Student management operations")
public class StudentController {

    private final StudentService studentService;
    private final ValidationUtil validationUtil;

    public StudentController(StudentService studentService, ValidationUtil validationUtil) {
        this.studentService = studentService;
        this.validationUtil = validationUtil;
    }

    @PostMapping
    @Operation(summary = "Create a student", description = "Creates a new student record")
    public ResponseEntity<?> save(@RequestBody StudentRequestDto studentDto) throws BusinessException {
        validationUtil.validate(studentDto);
        StudentResponseDto rsDto = studentService.create(studentDto);
        return new ResponseEntity<>(rsDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all students", description = "Returns all registered students")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-id")
    @Operation(summary = "Find a student by ID", description = "Returns the student matching the supplied ID")
    public ResponseEntity<?> findById(
            @Parameter(description = "Student ID")
            @RequestParam(value = "id", required = false) Integer id)
            throws BusinessException {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(summary = "Delete a student by ID", description = "Deletes the student matching the supplied ID")
    public ResponseEntity<?> deleteById(
            @Parameter(description = "Student ID")
            @RequestParam(value = "id", required = false) Integer id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
