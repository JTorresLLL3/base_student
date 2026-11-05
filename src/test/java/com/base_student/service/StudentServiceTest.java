package com.base_student.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.base_student.dto.StudentRequestDto;
import com.base_student.dto.StudentResponseDto;
import com.base_student.exception.BusinessException;
import com.base_student.model.StudentModel;
import com.base_student.respository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("Prueba de create: Validación correcta de persistencia y verificación de atributos")
    void testCreate() {
        StudentRequestDto requestDto = new StudentRequestDto(null, "John", "Doe", "+52 123 11 22 33", "j.doe@email.com");
        StudentModel savedModel = new StudentModel(1, "John", "Doe", "+52 123 11 22 33", "j.doe@email.com");

        when(studentRepository.existsByEmail(requestDto.eMail())).thenReturn(false);
        when(studentRepository.save(any(StudentModel.class))).thenReturn(savedModel);

        StudentResponseDto responseDto = studentService.create(requestDto);

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.id()).isEqualTo(1);
        assertThat(responseDto.name()).isEqualTo("John");
        assertThat(responseDto.lastName()).isEqualTo("Doe");
        assertThat(responseDto.phone()).isEqualTo("+52 123 11 22 33");
        assertThat(responseDto.eMail()).isEqualTo("j.doe@email.com");

        verify(studentRepository).existsByEmail(requestDto.eMail());
        verify(studentRepository).save(any(StudentModel.class));
    }

    @Test
    @DisplayName("Prueba de create: Debería lanzar BusinessException cuando el correo ya existe")
    void testCreateDuplicateEmailThrowsException() {
        StudentRequestDto requestDto = new StudentRequestDto(1, "John", "Doe", "+52 123 11 22 33", "j.doe@email.com");

        when(studentRepository.existsByEmail(requestDto.eMail())).thenReturn(true);

        assertThatThrownBy(() -> studentService.create(requestDto))
                .isInstanceOf(BusinessException.class);

        verify(studentRepository).existsByEmail(requestDto.eMail());
    }

    @Test
    @DisplayName("Prueba de findAll: Simulación adecuada del repositorio y validación exitosa de la lista recuperada")
    void testFindAll() {
        StudentModel student1 = new StudentModel(1, "Floor", "Jansen", "+52 123 11 22 33", "f.jansen@gmail.com");
        StudentModel student2 = new StudentModel(2, "Tarja", "Turunen", "+52 123 44 55 66", "t.turunen@gmail.com");

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        List<StudentResponseDto> responseDtos = studentService.findAll();

        assertThat(responseDtos)
                .isNotNull()
                .hasSize(2);

        assertThat(responseDtos.get(0).id()).isEqualTo(1);
        assertThat(responseDtos.get(0).name()).isEqualTo("Floor");
        assertThat(responseDtos.get(0).lastName()).isEqualTo("Jansen");
        assertThat(responseDtos.get(0).phone()).isEqualTo("+52 123 11 22 33");
        assertThat(responseDtos.get(0).eMail()).isEqualTo("f.jansen@gmail.com");

        assertThat(responseDtos.get(1).id()).isEqualTo(2);
        assertThat(responseDtos.get(1).name()).isEqualTo("Tarja");
        assertThat(responseDtos.get(1).lastName()).isEqualTo("Turunen");
        assertThat(responseDtos.get(1).phone()).isEqualTo("+52 123 44 55 66");
        assertThat(responseDtos.get(1).eMail()).isEqualTo("t.turunen@gmail.com");

        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("Prueba de findById: Retorna el estudiante correspondiente cuando el ID existe")
    void testFindById() throws BusinessException {
        Integer id = 1;
        StudentModel studentModel = new StudentModel(id, "John", "Doe", "+52 123 11 22 33", "j.doe@mail.com");

        when(studentRepository.findById(id)).thenReturn(Optional.of(studentModel));

        StudentResponseDto responseDto = studentService.findById(id);

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.id()).isEqualTo(1);
        assertThat(responseDto.name()).isEqualTo("John");
        assertThat(responseDto.lastName()).isEqualTo("Doe");
        assertThat(responseDto.phone()).isEqualTo("+52 123 11 22 33");
        assertThat(responseDto.eMail()).isEqualTo("j.doe@mail.com");

        verify(studentRepository).findById(id);
    }

    @Test
    @DisplayName("Prueba de findById: Lanza BusinessException cuando el ID no existe")
    void testFindByIdNotFound() {
        Integer id = 99;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.findById(id))
                .isInstanceOf(BusinessException.class);

        verify(studentRepository).findById(id);
    }

    @Test
    @DisplayName("Prueba de deleteById: Invoca la eliminación en el repositorio")
    void testDeleteById() {
        Integer id = 1;

        studentService.deleteById(id);

        verify(studentRepository).deleteById(id);
    }
}
