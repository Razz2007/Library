package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Reservation;
import com.racinger.librarySystem.Library.Service.interfaces.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "📅 Reservations", description = "Sistema de reservas de libros")
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping
    @Operation(
        summary = "📅 Obtener todas las reservas",
        description = "Retorna una lista completa de todas las reservas registradas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener reserva por ID",
        description = "Retorna la información completa de una reserva específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reserva encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Reserva no encontrada con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Reservation> getReservationById(@Parameter(description = "ID único de la reserva a buscar", example = "1") @PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nueva reserva",
        description = "Crea una nueva reserva de libro para un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Reserva creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Libro no disponible para reserva o estudiante no puede reservar"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Reservation> createReservation(@Parameter(description = "ID del estudiante", example = "1") @RequestParam Long studentId,
                                                         @Parameter(description = "ID del libro", example = "1") @RequestParam Long bookId) {
        try {
            Reservation reservation = reservationService.createReservation(studentId, bookId);
            return ResponseEntity.ok(reservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/cancel")
    @Operation(
        summary = "❌ Cancelar reserva",
        description = "Cancela una reserva activa"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Reserva cancelada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Reserva no encontrada o no se puede cancelar"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> cancelReservation(@Parameter(description = "ID de la reserva a cancelar", example = "1") @PathVariable Long id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/fulfill")
    @Operation(
        summary = "✅ Cumplir reserva",
        description = "Marca una reserva como cumplida (cuando el libro está disponible)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Reserva cumplida exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Reserva no encontrada o no se puede cumplir"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> fulfillReservation(@Parameter(description = "ID de la reserva a cumplir", example = "1") @PathVariable Long id) {
        try {
            reservationService.fulfillReservation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    @Operation(
        summary = "📅 Obtener reservas por estudiante",
        description = "Retorna todas las reservas de un estudiante específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getReservationsByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        List<Reservation> reservations = reservationService.findReservationsByStudent(studentId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/book/{bookId}")
    @Operation(
        summary = "📖 Obtener reservas por libro",
        description = "Retorna el historial de reservas de un libro específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getReservationsByBook(@Parameter(description = "ID del libro", example = "1") @PathVariable Long bookId) {
        List<Reservation> reservations = reservationService.findReservationsByBook(bookId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/student/{studentId}/active")
    @Operation(
        summary = "📅 Obtener reservas activas por estudiante",
        description = "Retorna las reservas activas de un estudiante"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas activas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getActiveReservationsByStudent(@Parameter(description = "ID del estudiante", example = "1") @PathVariable Long studentId) {
        List<Reservation> reservations = reservationService.findActiveReservationsByStudent(studentId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/expired")
    @Operation(
        summary = "⏰ Obtener reservas expiradas",
        description = "Retorna una lista de reservas que han expirado sin ser cumplidas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas expiradas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getExpiredReservations() {
        List<Reservation> reservations = reservationService.findExpiredReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "📊 Obtener reservas por estado",
        description = "Retorna reservas filtradas por su estado (ACTIVE, FULFILLED, CANCELLED, EXPIRED)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de reservas obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@Parameter(description = "Estado de la reserva", example = "ACTIVE") @PathVariable Reservation.ReservationStatus status) {
        List<Reservation> reservations = reservationService.findReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/check-student-limit")
    @Operation(
        summary = "👤 Verificar límite de reservas del estudiante",
        description = "Verifica si un estudiante puede realizar más reservas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Límite verificado exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> checkStudentReservationLimit(@Parameter(description = "ID del estudiante", example = "1") @RequestParam Long studentId) {
        boolean canReserve = reservationService.canStudentReserve(studentId);
        return ResponseEntity.ok(canReserve);
    }

    @GetMapping("/check-book-reservable")
    @Operation(
        summary = "📖 Verificar si libro se puede reservar",
        description = "Verifica si un libro está disponible para reserva"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Disponibilidad verificada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Boolean> checkBookReservable(@Parameter(description = "ID del libro", example = "1") @RequestParam Long bookId) {
        boolean reservable = reservationService.isBookReservable(bookId);
        return ResponseEntity.ok(reservable);
    }
}