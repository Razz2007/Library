package com.racinger.librarySystem.Library.DTO;

import java.time.LocalDate;

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

    public ReportDto() {}

    public ReportDto(String reportType, LocalDate startDate, LocalDate endDate, Long totalBooks, Long totalStudents, Long totalLoans, Long totalReturns, Double totalPenalties, Long totalReservations, Double totalPenaltyAmount, Long overdueLoans, Long activeLoans) {
        this.reportType = reportType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalBooks = totalBooks;
        this.totalStudents = totalStudents;
        this.totalLoans = totalLoans;
        this.totalReturns = totalReturns;
        this.totalPenalties = totalPenalties;
        this.totalReservations = totalReservations;
        this.totalPenaltyAmount = totalPenaltyAmount;
        this.overdueLoans = overdueLoans;
        this.activeLoans = activeLoans;
    }

    // Getters and setters
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Long getTotalBooks() { return totalBooks; }
    public void setTotalBooks(Long totalBooks) { this.totalBooks = totalBooks; }

    public Long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(Long totalStudents) { this.totalStudents = totalStudents; }

    public Long getTotalLoans() { return totalLoans; }
    public void setTotalLoans(Long totalLoans) { this.totalLoans = totalLoans; }

    public Long getTotalReturns() { return totalReturns; }
    public void setTotalReturns(Long totalReturns) { this.totalReturns = totalReturns; }

    public Double getTotalPenalties() { return totalPenalties; }
    public void setTotalPenalties(Double totalPenalties) { this.totalPenalties = totalPenalties; }

    public Long getTotalReservations() { return totalReservations; }
    public void setTotalReservations(Long totalReservations) { this.totalReservations = totalReservations; }

    public Double getTotalPenaltyAmount() { return totalPenaltyAmount; }
    public void setTotalPenaltyAmount(Double totalPenaltyAmount) { this.totalPenaltyAmount = totalPenaltyAmount; }

    public Long getOverdueLoans() { return overdueLoans; }
    public void setOverdueLoans(Long overdueLoans) { this.overdueLoans = overdueLoans; }

    public Long getActiveLoans() { return activeLoans; }
    public void setActiveLoans(Long activeLoans) { this.activeLoans = activeLoans; }
}