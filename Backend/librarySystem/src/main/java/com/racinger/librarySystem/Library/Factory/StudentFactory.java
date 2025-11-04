package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.StudentDto;
import com.racinger.librarySystem.Library.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {

    public StudentDto createStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setRegistrationDate(student.getRegistrationDate());
        dto.setIsActive(student.getIsActive());
        return dto;
    }

    public Student createStudentEntity(StudentDto dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setRegistrationDate(dto.getRegistrationDate());
        student.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return student;
    }
}