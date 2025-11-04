package com.racinger.librarySystem.Reporting.Controller;

import com.racinger.librarySystem.Library.DTO.ReportDto;
import com.racinger.librarySystem.Reporting.Service.interfaces.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    @GetMapping("/general")
    public ResponseEntity<ReportDto> getGeneralReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generateGeneralReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/books")
    public ResponseEntity<ReportDto> getBookReport() {
        ReportDto report = reportService.generateBookReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/students")
    public ResponseEntity<ReportDto> getStudentReport() {
        ReportDto report = reportService.generateStudentReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/loans")
    public ResponseEntity<ReportDto> getLoanReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generateLoanReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/penalties")
    public ResponseEntity<ReportDto> getPenaltyReport(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generatePenaltyReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/overdue")
    public ResponseEntity<ReportDto> getOverdueReport() {
        ReportDto report = reportService.generateOverdueReport();
        return ResponseEntity.ok(report);
    }
}