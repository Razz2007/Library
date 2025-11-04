package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Student;
import com.racinger.librarySystem.Library.Service.interfaces.IStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "👥 Students", description = "Gestión de estudiantes y usuarios del sistema")
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    @Operation(
        summary = "👥 Obtener todos los estudiantes",
        description = "Retorna una lista completa de todos los estudiantes registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de estudiantes obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener estudiante por ID",
        description = "Retorna la información completa de un estudiante específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Estudiante encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Student> getStudentById(@Parameter(description = "ID único del estudiante a buscar", example = "1") @PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nuevo estudiante",
        description = "Crea un nuevo estudiante en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Estudiante creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Email ya existe o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (studentService.existsByEmail(student.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        Student savedStudent = studentService.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "✏️ Actualizar estudiante",
        description = "Actualiza la información de un estudiante existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Estudiante actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Student> updateStudent(@Parameter(description = "ID del estudiante a actualizar", example = "1") @PathVariable Long id, @RequestBody Student student) {
        if (!studentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        student.setId(id);
        Student updatedStudent = studentService.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "🗑️ Eliminar estudiante",
        description = "Elimina un estudiante del sistema por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Estudiante eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> deleteStudent(@Parameter(description = "ID del estudiante a eliminar", example = "1") @PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    @Operation(
        summary = "📧 Obtener estudiante por email",
        description = "Busca un estudiante por su dirección de email"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Estudiante encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado con el email especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Student> getStudentByEmail(@Parameter(description = "Email del estudiante", example = "estudiante@universidad.edu") @PathVariable String email) {
        Optional<Student> student = studentService.findByEmail(email);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(
        summary = "🔍 Buscar estudiantes",
        description = "Busca estudiantes por nombre, apellido o email"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Student>> searchStudents(@Parameter(description = "Término de búsqueda (nombre, apellido o email)", example = "Juan") @RequestParam String term) {
        List<Student> students = studentService.searchStudents(term);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/active")
    @Operation(
        summary = "✅ Obtener estudiantes activos",
        description = "Retorna una lista de estudiantes que están activos en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de estudiantes activos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Student>> getActiveStudents() {
        List<Student> students = studentService.findActiveStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(
        summary = "🚫 Desactivar estudiante",
        description = "Desactiva un estudiante en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Estudiante desactivado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> deactivateStudent(@Parameter(description = "ID del estudiante a desactivar", example = "1") @PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentService.deactivateStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activate")
    @Operation(
        summary = "✅ Activar estudiante",
        description = "Activa un estudiante en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Estudiante activado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Estudiante no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> activateStudent(@Parameter(description = "ID del estudiante a activar", example = "1") @PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        studentService.activateStudent(id);
        return ResponseEntity.noContent().build();
    }
}