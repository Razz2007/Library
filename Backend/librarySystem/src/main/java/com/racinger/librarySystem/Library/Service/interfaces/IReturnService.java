package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Return;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReturnService {

    List<Return> findAll();

    Optional<Return> findById(Long id);

    Return save(Return returnEntity);

    void deleteById(Long id);

    List<Return> findReturnsByLoan(Long loanId);

    List<Return> findReturnsInDateRange(LocalDate startDate, LocalDate endDate);

    Return processReturn(Long loanId, LocalDate returnDate);

    double calculatePenaltyAmount(int daysLate);

    boolean isReturnLate(LocalDate dueDate, LocalDate returnDate);
}