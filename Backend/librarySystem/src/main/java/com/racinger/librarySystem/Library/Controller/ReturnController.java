package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Return;
import com.racinger.librarySystem.Library.Service.interfaces.IReturnService;
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
@RequestMapping("/api/returns")
@RequiredArgsConstructor
@Tag(name = "↩️ Returns", description = "Sistema de devoluciones de libros")
public class ReturnController {

    private final IReturnService returnService;

    @GetMapping
    @Operation(
        summary = "↩️ Obtener todas las devoluciones",
        description = "Retorna una lista completa de todas las devoluciones registradas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de devoluciones obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Return>> getAllReturns() {
        List<Return> returns = returnService.findAll();
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener devolución por ID",
        description = "Retorna la información completa de una devolución específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Devolución encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Devolución no encontrada con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Return> getReturnById(@Parameter(description = "ID único de la devolución a buscar", example = "1") @PathVariable Long id) {
        Optional<Return> returnEntity = returnService.findById(id);
        return returnEntity.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Procesar devolución",
        description = "Procesa la devolución de un libro prestado"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Devolución procesada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Préstamo no encontrado o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Return> processReturn(@Parameter(description = "ID del préstamo", example = "1") @RequestParam Long loanId,
                                                @Parameter(description = "Fecha de devolución (YYYY-MM-DD)", example = "2024-11-15") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        try {
            Return returnEntity = returnService.processReturn(loanId, returnDate);
            return ResponseEntity.ok(returnEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/loan/{loanId}")
    @Operation(
        summary = "↩️ Obtener devoluciones por préstamo",
        description = "Retorna las devoluciones asociadas a un préstamo específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de devoluciones obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Return>> getReturnsByLoan(@Parameter(description = "ID del préstamo", example = "1") @PathVariable Long loanId) {
        List<Return> returns = returnService.findReturnsByLoan(loanId);
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/date-range")
    @Operation(
        summary = "📅 Obtener devoluciones por rango de fechas",
        description = "Retorna las devoluciones realizadas dentro de un rango de fechas específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de devoluciones obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Return>> getReturnsInDateRange(@Parameter(description = "Fecha de inicio (YYYY-MM-DD)", example = "2024-11-01") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                               @Parameter(description = "Fecha de fin (YYYY-MM-DD)", example = "2024-11-30") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Return> returns = returnService.findReturnsInDateRange(startDate, endDate);
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/calculate-penalty")
    @Operation(
        summary = "💰 Calcular multa",
        description = "Calcula el monto de la multa por días de retraso"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Multa calculada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Double> calculatePenalty(@Parameter(description = "Número de días de retraso", example = "5") @RequestParam int daysLate) {
        double penalty = returnService.calculatePenaltyAmount(daysLate);
        return ResponseEntity.ok(penalty);
    }

    @GetMapping("/check-late")
    @Operation(
        summary = "⏰ Verificar si devolución es tardía",
        description = "Verifica si una devolución se considera tardía comparando fechas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Verificación realizada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> checkIfReturnIsLate(@Parameter(description = "Fecha de vencimiento (YYYY-MM-DD)", example = "2024-11-10") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                                                        @Parameter(description = "Fecha de devolución (YYYY-MM-DD)", example = "2024-11-15") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        boolean isLate = returnService.isReturnLate(dueDate, returnDate);
        return ResponseEntity.ok(isLate);
    }
}