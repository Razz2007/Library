package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Penalty;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPenaltyService {

    List<Penalty> findAll();

    Optional<Penalty> findById(Long id);

    Penalty save(Penalty penalty);

    void deleteById(Long id);

    List<Penalty> findPenaltiesByStudent(Long studentId);

    List<Penalty> findPenaltiesByStatus(Penalty.PenaltyStatus status);

    List<Penalty> findPenaltiesInDateRange(LocalDate startDate, LocalDate endDate);

    Penalty createPenalty(Long studentId, Double amount, String reason);

    void payPenalty(Long penaltyId);

    Double getTotalPendingPenaltiesByStudent(Long studentId);

    boolean hasPendingPenalties(Long studentId);
}