package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByStudentId(Long studentId);

    List<Loan> findByBookId(Long bookId);

    List<Loan> findByStatus(Loan.LoanStatus status);

    @Query("SELECT l FROM Loan l WHERE l.dueDate < :currentDate AND l.status = 'ACTIVE'")
    List<Loan> findOverdueLoans(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT l FROM Loan l WHERE l.student.id = :studentId AND l.status = 'ACTIVE'")
    List<Loan> findActiveLoansByStudent(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.status = 'ACTIVE'")
    Long countActiveLoans();

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.dueDate < :currentDate AND l.status = 'ACTIVE'")
    Long countOverdueLoans(@Param("currentDate") LocalDate currentDate);
}