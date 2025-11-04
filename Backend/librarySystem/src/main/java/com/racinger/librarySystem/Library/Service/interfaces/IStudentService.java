package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService {

    List<Student> findAll();

    Optional<Student> findById(Long id);

    Student save(Student student);

    void deleteById(Long id);

    Optional<Student> findByEmail(String email);

    List<Student> searchStudents(String searchTerm);

    List<Student> findActiveStudents();

    boolean existsByEmail(String email);

    void deactivateStudent(Long studentId);

    void activateStudent(Long studentId);
}