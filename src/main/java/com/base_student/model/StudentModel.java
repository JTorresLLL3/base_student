package com.base_student.model;

import com.base_student.dto.StudentResponseDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import tools.jackson.databind.ObjectMapper;

@Entity
@Table(name = "student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "varchar(80)")
    private String name;
    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String lastName;
    @Column(name = "phone", columnDefinition = "varchar(20)")
    private String phone;
    @Column(name = "email", columnDefinition = "varchar(80)", unique = true)
    private String email;

    public StudentModel() {
    }

    public StudentModel(Integer id, String name, String lastName, String phone, String eMail) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = eMail;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public StudentResponseDto toDto() {
        return new StudentResponseDto(
                this.id,
                this.name,
                this.lastName,
                this.phone,
                this.email);
    }

    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
