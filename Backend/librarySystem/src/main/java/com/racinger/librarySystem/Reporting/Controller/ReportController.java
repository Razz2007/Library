package com.racinger.librarySystem.Reporting.Controller;

import com.racinger.librarySystem.Library.DTO.ReportDto;
import com.racinger.librarySystem.Reporting.Service.interfaces.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "📊 Reports", description = "Sistema de reportes y estadísticas")
public class ReportController {

    private final IReportService reportService;

    @GetMapping("/general")
    @Operation(
        summary = "📊 Reporte general",
        description = "Genera un reporte general del sistema con estadísticas completas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getGeneralReport(@Parameter(description = "Fecha de inicio (opcional, formato YYYY-MM-DD)", example = "2024-11-01") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @Parameter(description = "Fecha de fin (opcional, formato YYYY-MM-DD)", example = "2024-11-30") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generateGeneralReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/books")
    @Operation(
        summary = "📚 Reporte de libros",
        description = "Genera un reporte con estadísticas de libros y disponibilidad"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getBookReport() {
        ReportDto report = reportService.generateBookReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/students")
    @Operation(
        summary = "👥 Reporte de estudiantes",
        description = "Genera un reporte con estadísticas de estudiantes y actividad"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getStudentReport() {
        ReportDto report = reportService.generateStudentReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/loans")
    @Operation(
        summary = "📋 Reporte de préstamos",
        description = "Genera un reporte con estadísticas de préstamos en un período"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getLoanReport(@Parameter(description = "Fecha de inicio (opcional, formato YYYY-MM-DD)", example = "2024-11-01") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                   @Parameter(description = "Fecha de fin (opcional, formato YYYY-MM-DD)", example = "2024-11-30") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generateLoanReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/penalties")
    @Operation(
        summary = "💰 Reporte de multas",
        description = "Genera un reporte con estadísticas de multas en un período"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getPenaltyReport(@Parameter(description = "Fecha de inicio (opcional, formato YYYY-MM-DD)", example = "2024-11-01") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                      @Parameter(description = "Fecha de fin (opcional, formato YYYY-MM-DD)", example = "2024-11-30") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ReportDto report = reportService.generatePenaltyReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/overdue")
    @Operation(
        summary = "⏰ Reporte de préstamos vencidos",
        description = "Genera un reporte con todos los préstamos que han excedido la fecha de vencimiento"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reporte generado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<ReportDto> getOverdueReport() {
        ReportDto report = reportService.generateOverdueReport();
        return ResponseEntity.ok(report);
    }
}