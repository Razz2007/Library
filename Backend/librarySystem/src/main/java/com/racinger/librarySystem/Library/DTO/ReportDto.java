package com.racinger.librarySystem.Library.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    private String reportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long totalBooks;
    private Long totalStudents;
    private Long totalLoans;
    private Long totalReturns;
    private Double totalPenalties;
    private Long totalReservations;
    private Double totalPenaltyAmount;
    private Long overdueLoans;
    private Long activeLoans;
}