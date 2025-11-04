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
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.findById(id);
        return loan.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam Long studentId,
                                           @RequestParam Long bookId,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        try {
            Loan loan = loanService.createLoan(studentId, bookId, dueDate);
            return ResponseEntity.ok(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        try {
            Loan loan = loanService.returnBook(id, returnDate);
            return ResponseEntity.ok(loan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Loan>> getLoansByStudent(@PathVariable Long studentId) {
        List<Loan> loans = loanService.findLoansByStudent(studentId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> getLoansByBook(@PathVariable Long bookId) {
        List<Loan> loans = loanService.findLoansByBook(bookId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/student/{studentId}/active")
    public ResponseEntity<List<Loan>> getActiveLoansByStudent(@PathVariable Long studentId) {
        List<Loan> loans = loanService.findActiveLoansByStudent(studentId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        List<Loan> loans = loanService.findOverdueLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
        List<Loan> loans = loanService.findLoansByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkBookAvailability(@RequestParam Long bookId) {
        boolean available = loanService.isBookAvailableForLoan(bookId);
        return ResponseEntity.ok(available);
    }

    @GetMapping("/check-student-limit")
    public ResponseEntity<Boolean> checkStudentLoanLimit(@RequestParam Long studentId) {
        boolean canBorrow = loanService.canStudentBorrow(studentId);
        return ResponseEntity.ok(canBorrow);
    }

    @GetMapping("/student/{studentId}/count")
    public ResponseEntity<Integer> getActiveLoanCountByStudent(@PathVariable Long studentId) {
        int count = loanService.getActiveLoansCountByStudent(studentId);
        return ResponseEntity.ok(count);
    }
}