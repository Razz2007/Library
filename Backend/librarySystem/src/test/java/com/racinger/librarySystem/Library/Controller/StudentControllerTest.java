package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Student;
import com.racinger.librarySystem.Library.Service.interfaces.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private IStudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student sampleStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        sampleStudent = new Student();
        sampleStudent.setId(1L);
        sampleStudent.setFirstName("Juan");
        sampleStudent.setLastName("Pérez");
        sampleStudent.setEmail("juan.perez@email.com");
        sampleStudent.setPhone("+57 300 123 4567");
        sampleStudent.setRegistrationDate(LocalDate.of(2023, 1, 15));
        sampleStudent.setIsActive(true);
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudents() {
        // Arrange
        List<Student> students = Arrays.asList(sampleStudent);
        when(studentService.findAll()).thenReturn(students);

        // Act
        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(studentService, times(1)).findAll();
    }

    @Test
    void getStudentById_WhenStudentExists_ShouldReturnStudent() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.of(sampleStudent));

        // Act
        ResponseEntity<Student> response = studentController.getStudentById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getFirstName());
        assertEquals("Pérez", response.getBody().getLastName());
        verify(studentService, times(1)).findById(1L);
    }

    @Test
    void getStudentById_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Student> response = studentController.getStudentById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(studentService, times(1)).findById(1L);
    }

    @Test
    void createStudent_WhenEmailDoesNotExist_ShouldReturnCreatedStudent() {
        // Arrange
        when(studentService.existsByEmail("juan.perez@email.com")).thenReturn(false);
        when(studentService.save(any(Student.class))).thenReturn(sampleStudent);

        // Act
        ResponseEntity<Student> response = studentController.createStudent(sampleStudent);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getFirstName());
        verify(studentService, times(1)).existsByEmail("juan.perez@email.com");
        verify(studentService, times(1)).save(any(Student.class));
    }

    @Test
    void createStudent_WhenEmailExists_ShouldReturnBadRequest() {
        // Arrange
        when(studentService.existsByEmail("juan.perez@email.com")).thenReturn(true);

        // Act
        ResponseEntity<Student> response = studentController.createStudent(sampleStudent);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(studentService, times(1)).existsByEmail("juan.perez@email.com");
        verify(studentService, never()).save(any(Student.class));
    }

    @Test
    void updateStudent_WhenStudentExists_ShouldReturnUpdatedStudent() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.of(sampleStudent));
        when(studentService.save(any(Student.class))).thenReturn(sampleStudent);

        // Act
        ResponseEntity<Student> response = studentController.updateStudent(1L, sampleStudent);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(studentService, times(1)).save(any(Student.class));
    }

    @Test
    void updateStudent_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Student> response = studentController.updateStudent(1L, sampleStudent);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(studentService, times(1)).findById(1L);
        verify(studentService, never()).save(any(Student.class));
    }

    @Test
    void deleteStudent_WhenStudentExists_ShouldReturnNoContent() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.of(sampleStudent));
        doNothing().when(studentService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = studentController.deleteStudent(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deleteById(1L);
    }

    @Test
    void deleteStudent_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = studentController.deleteStudent(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).findById(1L);
        verify(studentService, never()).deleteById(anyLong());
    }

    @Test
    void getStudentByEmail_WhenStudentExists_ShouldReturnStudent() {
        // Arrange
        when(studentService.findByEmail("juan.perez@email.com")).thenReturn(Optional.of(sampleStudent));

        // Act
        ResponseEntity<Student> response = studentController.getStudentByEmail("juan.perez@email.com");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("juan.perez@email.com", response.getBody().getEmail());
        verify(studentService, times(1)).findByEmail("juan.perez@email.com");
    }

    @Test
    void getStudentByEmail_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findByEmail("juan.perez@email.com")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Student> response = studentController.getStudentByEmail("juan.perez@email.com");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(studentService, times(1)).findByEmail("juan.perez@email.com");
    }

    @Test
    void searchStudents_ShouldReturnMatchingStudents() {
        // Arrange
        List<Student> students = Arrays.asList(sampleStudent);
        when(studentService.searchStudents("Juan")).thenReturn(students);

        // Act
        ResponseEntity<List<Student>> response = studentController.searchStudents("Juan");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(studentService, times(1)).searchStudents("Juan");
    }

    @Test
    void getActiveStudents_ShouldReturnActiveStudents() {
        // Arrange
        List<Student> students = Arrays.asList(sampleStudent);
        when(studentService.findActiveStudents()).thenReturn(students);

        // Act
        ResponseEntity<List<Student>> response = studentController.getActiveStudents();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(studentService, times(1)).findActiveStudents();
    }

    @Test
    void deactivateStudent_WhenStudentExists_ShouldReturnNoContent() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.of(sampleStudent));
        doNothing().when(studentService).deactivateStudent(1L);

        // Act
        ResponseEntity<Void> response = studentController.deactivateStudent(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deactivateStudent(1L);
    }

    @Test
    void deactivateStudent_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = studentController.deactivateStudent(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).findById(1L);
        verify(studentService, never()).deactivateStudent(anyLong());
    }

    @Test
    void activateStudent_WhenStudentExists_ShouldReturnNoContent() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.of(sampleStudent));
        doNothing().when(studentService).activateStudent(1L);

        // Act
        ResponseEntity<Void> response = studentController.activateStudent(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).activateStudent(1L);
    }

    @Test
    void activateStudent_WhenStudentDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(studentService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = studentController.activateStudent(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).findById(1L);
        verify(studentService, never()).activateStudent(anyLong());
    }
}