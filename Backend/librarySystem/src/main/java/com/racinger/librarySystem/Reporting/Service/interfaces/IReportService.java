package com.racinger.librarySystem.Reporting.Service.interfaces;

import com.racinger.librarySystem.Library.DTO.ReportDto;
import java.time.LocalDate;

public interface IReportService {

    ReportDto generateGeneralReport(LocalDate startDate, LocalDate endDate);

    ReportDto generateBookReport();

    ReportDto generateStudentReport();

    ReportDto generateLoanReport(LocalDate startDate, LocalDate endDate);

    ReportDto generatePenaltyReport(LocalDate startDate, LocalDate endDate);

    ReportDto generateOverdueReport();
}