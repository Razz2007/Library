package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Loan;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILoanService {

    List<Loan> findAll();

    Optional<Loan> findById(Long id);

    Loan save(Loan loan);

    void deleteById(Long id);

    List<Loan> findLoansByStudent(Long studentId);

    List<Loan> findLoansByBook(Long bookId);

    List<Loan> findActiveLoansByStudent(Long studentId);

    List<Loan> findOverdueLoans();

    List<Loan> findLoansByStatus(Loan.LoanStatus status);

    Loan createLoan(Long studentId, Long bookId, LocalDate dueDate);

    Loan returnBook(Long loanId, LocalDate returnDate);

    boolean isBookAvailableForLoan(Long bookId);

    boolean canStudentBorrow(Long studentId);

    int getActiveLoansCountByStudent(Long studentId);
}