package com.racinger.librarySystem.Reporting.Service.impl;

import com.racinger.librarySystem.Library.DTO.ReportDto;
import com.racinger.librarySystem.Library.Factory.ReportFactory;
import com.racinger.librarySystem.Library.Repository.*;
import com.racinger.librarySystem.Reporting.Service.interfaces.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final LoanRepository loanRepository;
    private final ReturnRepository returnRepository;
    private final PenaltyRepository penaltyRepository;
    private final ReservationRepository reservationRepository;
    private final ReportFactory reportFactory;

    @Override
    public ReportDto generateGeneralReport(LocalDate startDate, LocalDate endDate) {
        ReportDto report = reportFactory.createReportDto("GENERAL");
        report.setStartDate(startDate);
        report.setEndDate(endDate);

        // General statistics
        report.setTotalBooks(bookRepository.count());
        report.setTotalStudents(studentRepository.countActiveStudents());
        report.setTotalLoans(loanRepository.countActiveLoans());
        report.setTotalReturns(returnRepository.countReturnsSince(startDate));
        report.setTotalPenalties(penaltyRepository.sumPendingPenalties());
        report.setTotalReservations(reservationRepository.countActiveReservations());

        // Date range statistics
        if (startDate != null && endDate != null) {
            report.setTotalPenaltyAmount(returnRepository.sumPenaltiesInDateRange(startDate, endDate));
        }

        report.setOverdueLoans(loanRepository.countOverdueLoans(LocalDate.now()));

        return report;
    }

    @Override
    public ReportDto generateBookReport() {
        ReportDto report = reportFactory.createReportDto("BOOKS");
        report.setTotalBooks(bookRepository.count());
        return report;
    }

    @Override
    public ReportDto generateStudentReport() {
        ReportDto report = reportFactory.createReportDto("STUDENTS");
        report.setTotalStudents(studentRepository.countActiveStudents());
        return report;
    }

    @Override
    public ReportDto generateLoanReport(LocalDate startDate, LocalDate endDate) {
        ReportDto report = reportFactory.createReportDto("LOANS");
        report.setStartDate(startDate);
        report.setEndDate(endDate);

        report.setTotalLoans(loanRepository.countActiveLoans());
        report.setOverdueLoans(loanRepository.countOverdueLoans(LocalDate.now()));

        return report;
    }

    @Override
    public ReportDto generatePenaltyReport(LocalDate startDate, LocalDate endDate) {
        ReportDto report = reportFactory.createReportDto("PENALTIES");
        report.setStartDate(startDate);
        report.setEndDate(endDate);

        if (startDate != null && endDate != null) {
            report.setTotalPenaltyAmount(returnRepository.sumPenaltiesInDateRange(startDate, endDate));
        }

        report.setTotalPenalties(penaltyRepository.sumPendingPenalties());

        return report;
    }

    @Override
    public ReportDto generateOverdueReport() {
        ReportDto report = reportFactory.createReportDto("OVERDUE");
        report.setOverdueLoans(loanRepository.countOverdueLoans(LocalDate.now()));
        return report;
    }
}