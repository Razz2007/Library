package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {

    List<Return> findByLoanId(Long loanId);

    @Query("SELECT r FROM Return r WHERE r.returnDate BETWEEN :startDate AND :endDate")
    List<Return> findReturnsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(r) FROM Return r WHERE r.returnDate >= :startDate")
    Long countReturnsSince(@Param("startDate") LocalDate startDate);

    @Query("SELECT SUM(r.penaltyAmount) FROM Return r WHERE r.returnDate BETWEEN :startDate AND :endDate")
    Double sumPenaltiesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}