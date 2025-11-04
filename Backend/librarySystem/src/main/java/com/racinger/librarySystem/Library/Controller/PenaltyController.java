package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Penalty;
import com.racinger.librarySystem.Library.Service.interfaces.IPenaltyService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
@Tag(name = "💰 Penalties", description = "Sistema de multas y penalizaciones")
public class PenaltyController {

    private final IPenaltyService penaltyService;

    @GetMapping
    @Operation(
        summary = "💰 Obtener todas las multas",
        description = "Retorna una lista completa de todas las multas registradas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de multas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Penalty>> getAllPenalties() {
        List<Penalty> penalties = penaltyService.findAll();
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener multa por ID",
        description = "Retorna la información completa de una multa específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Multa encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Multa no encontrada con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Penalty> getPenaltyById(@Parameter(description = "ID único de la multa a buscar", example = "1") @PathVariable Long id) {
        Optional<Penalty> penalty = penaltyService.findById(id);
        return penalty.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nueva multa",
        description = "Crea una nueva multa para un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Multa creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Estudiante no encontrado o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Penalty> createPenalty(@Parameter(description = "ID del estudiante", example = "1") @RequestParam Long studentId,
                                                 @Parameter(description = "Monto de la multa", example = "50.0") @RequestParam Double amount,
                                                 @Parameter(description = "Razón de la multa", example = "Devolución tardía") @RequestParam String reason) {
        try {
            Penalty penalty = penaltyService.createPenalty(studentId, amount, reason);
            return ResponseEntity.ok(penalty);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/pay")
    @Operation(
        summary = "💳 Pagar multa",
        description = "Marca una multa como pagada"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Multa pagada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Multa no encontrada o no se puede pagar"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> payPenalty(@Parameter(description = "ID de la multa a pagar", example = "1") @PathVariable Long id) {
        try {
            penaltyService.payPenalty(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    @Operation(
        summary = "💰 Obtener multas por estudiante",
        description = "Retorna todas las multas de un estudiante específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de multas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Penalty>> getPenaltiesByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        List<Penalty> penalties = penaltyService.findPenaltiesByStudent(studentId);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "📊 Obtener multas por estado",
        description = "Retorna multas filtradas por su estado (PENDING, PAID)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de multas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Penalty>> getPenaltiesByStatus(@Parameter(description = "Estado de la multa", example = "PENDING") @PathVariable Penalty.PenaltyStatus status) {
        List<Penalty> penalties = penaltyService.findPenaltiesByStatus(status);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/date-range")
    @Operation(
        summary = "📅 Obtener multas por rango de fechas",
        description = "Retorna las multas creadas dentro de un rango de fechas específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de multas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Penalty>> getPenaltiesInDateRange(@Parameter(description = "Fecha de inicio (YYYY-MM-DD)", example = "2024-11-01") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                  @Parameter(description = "Fecha de fin (YYYY-MM-DD)", example = "2024-11-30") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Penalty> penalties = penaltyService.findPenaltiesInDateRange(startDate, endDate);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/student/{studentId}/pending-total")
    @Operation(
        summary = "💰 Obtener total de multas pendientes por estudiante",
        description = "Retorna el monto total de multas pendientes de un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Total calculado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Double> getTotalPendingPenaltiesByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        Double total = penaltyService.getTotalPendingPenaltiesByStudent(studentId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/student/{studentId}/has-pending")
    @Operation(
        summary = "❓ Verificar multas pendientes",
        description = "Verifica si un estudiante tiene multas pendientes"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Verificación realizada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> hasPendingPenalties(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        boolean hasPending = penaltyService.hasPendingPenalties(studentId);
        return ResponseEntity.ok(hasPending);
    }
}