package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Loan;
import com.racinger.librarySystem.Library.Service.interfaces.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Tag(name = "📋 Loans", description = "Sistema de préstamos de libros")
public class LoanController {

    private final ILoanService loanService;

    @GetMapping
    @Operation(
        summary = "📋 Obtener todos los préstamos",
        description = "Retorna una lista completa de todos los préstamos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener préstamo por ID",
        description = "Retorna la información completa de un préstamo específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Préstamo encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Préstamo no encontrado con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Loan> getLoanById(@Parameter(description = "ID único del préstamo a buscar", example = "1") @PathVariable Long id) {
        Optional<Loan> loan = loanService.findById(id);
        return loan.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nuevo préstamo",
        description = "Crea un nuevo préstamo de libro para un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Préstamo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Libro no disponible, estudiante no puede prestar o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Loan> createLoan(@Parameter(description = "ID del estudiante", example = "1") @RequestParam Long studentId,
                                           @Parameter(description = "ID del libro", example = "1") @RequestParam Long bookId,
                                           @Parameter(description = "Fecha de vencimiento (YYYY-MM-DD)", example = "2024-12-31") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        try {
            Loan loan = loanService.createLoan(studentId, bookId, dueDate);
            return ResponseEntity.ok(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    @Operation(
        summary = "📚 Devolver libro",
        description = "Registra la devolución de un libro prestado"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Libro devuelto exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos o préstamo no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Loan> returnBook(@Parameter(description = "ID del préstamo", example = "1") @PathVariable Long id,
                                           @Parameter(description = "Fecha de devolución (YYYY-MM-DD)", example = "2024-11-15") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        try {
            Loan loan = loanService.returnBook(id, returnDate);
            return ResponseEntity.ok(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    @Operation(
        summary = "📋 Obtener préstamos por estudiante",
        description = "Retorna todos los préstamos de un estudiante específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getLoansByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        List<Loan> loans = loanService.findLoansByStudent(studentId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/book/{bookId}")
    @Operation(
        summary = "📖 Obtener préstamos por libro",
        description = "Retorna el historial de préstamos de un libro específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getLoansByBook(@Parameter(description = "ID del libro", example = "1") @PathVariable Long bookId) {
        List<Loan> loans = loanService.findLoansByBook(bookId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/student/{studentId}/active")
    @Operation(
        summary = "📋 Obtener préstamos activos por estudiante",
        description = "Retorna los préstamos activos (no devueltos) de un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos activos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getActiveLoansByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        List<Loan> loans = loanService.findActiveLoansByStudent(studentId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/overdue")
    @Operation(
        summary = "⏰ Obtener préstamos vencidos",
        description = "Retorna una lista de todos los préstamos que han excedido la fecha de vencimiento"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos vencidos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        List<Loan> loans = loanService.findOverdueLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "📊 Obtener préstamos por estado",
        description = "Retorna préstamos filtrados por su estado (ACTIVE, RETURNED, OVERDUE)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de préstamos obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Loan>> getLoansByStatus(@Parameter(description = "Estado del préstamo", example = "ACTIVE") @PathVariable Loan.LoanStatus status) {
        List<Loan> loans = loanService.findLoansByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/check-availability")
    @Operation(
        summary = "✅ Verificar disponibilidad de libro",
        description = "Verifica si un libro está disponible para préstamo"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Disponibilidad verificada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> checkBookAvailability(@Parameter(description = "ID del libro", example = "1") @RequestParam Long bookId) {
        boolean available = loanService.isBookAvailableForLoan(bookId);
        return ResponseEntity.ok(available);
    }

    @GetMapping("/check-student-limit")
    @Operation(
        summary = "👤 Verificar límite de préstamos del estudiante",
        description = "Verifica si un estudiante puede realizar más préstamos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Límite verificado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> checkStudentLoanLimit(@Parameter(description = "ID del estudiante", example = "1") @RequestParam Long studentId) {
        boolean canBorrow = loanService.canStudentBorrow(studentId);
        return ResponseEntity.ok(canBorrow);
    }

    @GetMapping("/student/{studentId}/count")
    @Operation(
        summary = "🔢 Contar préstamos activos por estudiante",
        description = "Retorna el número de préstamos activos de un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Conteo realizado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Integer> getActiveLoanCountByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        int count = loanService.getActiveLoansCountByStudent(studentId);
        return ResponseEntity.ok(count);
    }
}