package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    List<Penalty> findByStudentId(Long studentId);

    List<Penalty> findByStatus(Penalty.PenaltyStatus status);

    @Query("SELECT p FROM Penalty p WHERE p.issuedDate BETWEEN :startDate AND :endDate")
    List<Penalty> findPenaltiesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(p.amount) FROM Penalty p WHERE p.status = 'PENDING'")
    Double sumPendingPenalties();

    @Query("SELECT COUNT(p) FROM Penalty p WHERE p.student.id = :studentId AND p.status = 'PENDING'")
    Long countPendingPenaltiesByStudent(@Param("studentId") Long studentId);
}