package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Loan;
import com.racinger.librarySystem.Library.Entity.Return;
import com.racinger.librarySystem.Library.Repository.LoanRepository;
import com.racinger.librarySystem.Library.Repository.ReturnRepository;
import com.racinger.librarySystem.Library.Service.interfaces.ILoanService;
import com.racinger.librarySystem.Library.Service.interfaces.IReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReturnService implements IReturnService {

    private final ReturnRepository returnRepository;
    private final LoanRepository loanRepository;
    private final ILoanService loanService;

    private static final double PENALTY_PER_DAY = 1.0;

    @Override
    @Transactional(readOnly = true)
    public List<Return> findAll() {
        return returnRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Return> findById(Long id) {
        return returnRepository.findById(id);
    }

    @Override
    public Return save(Return returnEntity) {
        return returnRepository.save(returnEntity);
    }

    @Override
    public void deleteById(Long id) {
        returnRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Return> findReturnsByLoan(Long loanId) {
        return returnRepository.findByLoanId(loanId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Return> findReturnsInDateRange(LocalDate startDate, LocalDate endDate) {
        return returnRepository.findReturnsInDateRange(startDate, endDate);
    }

    @Override
    public Return processReturn(Long loanId, LocalDate returnDate) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            throw new IllegalArgumentException("Loan not found");
        }

        Loan loan = loanOpt.get();

        // Mark loan as returned
        loanService.returnBook(loanId, returnDate);

        // Calculate penalty if late
        int daysLate = 0;
        double penaltyAmount = 0.0;

        if (isReturnLate(loan.getDueDate(), returnDate)) {
            daysLate = (int) ChronoUnit.DAYS.between(loan.getDueDate(), returnDate);
            penaltyAmount = calculatePenaltyAmount(daysLate);
        }

        // Create return record
        Return returnEntity = new Return();
        returnEntity.setLoan(loan);
        returnEntity.setReturnDate(returnDate);
        returnEntity.setDaysLate(daysLate);
        returnEntity.setPenaltyAmount(penaltyAmount);

        return returnRepository.save(returnEntity);
    }

    @Override
    public double calculatePenaltyAmount(int daysLate) {
        return daysLate * PENALTY_PER_DAY;
    }

    @Override
    public boolean isReturnLate(LocalDate dueDate, LocalDate returnDate) {
        return returnDate.isAfter(dueDate);
    }
}