package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Reservation;
import com.racinger.librarySystem.Library.Service.interfaces.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestParam Long studentId,
                                                         @RequestParam Long bookId) {
        try {
            Reservation reservation = reservationService.createReservation(studentId, bookId);
            return ResponseEntity.ok(reservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<Void> fulfillReservation(@PathVariable Long id) {
        try {
            reservationService.fulfillReservation(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Reservation>> getReservationsByStudent(@PathVariable Long studentId) {
        List<Reservation> reservations = reservationService.findReservationsByStudent(studentId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Reservation>> getReservationsByBook(@PathVariable Long bookId) {
        List<Reservation> reservations = reservationService.findReservationsByBook(bookId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/student/{studentId}/active")
    public ResponseEntity<List<Reservation>> getActiveReservationsByStudent(@PathVariable Long studentId) {
        List<Reservation> reservations = reservationService.findActiveReservationsByStudent(studentId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Reservation>> getExpiredReservations() {
        List<Reservation> reservations = reservationService.findExpiredReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable Reservation.ReservationStatus status) {
        List<Reservation> reservations = reservationService.findReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/check-student-limit")
    public ResponseEntity<Boolean> checkStudentReservationLimit(@RequestParam Long studentId) {
        boolean canReserve = reservationService.canStudentReserve(studentId);
        return ResponseEntity.ok(canReserve);
    }

    @GetMapping("/check-book-reservable")
    public ResponseEntity<Boolean> checkBookReservable(@RequestParam Long bookId) {
        boolean reservable = reservationService.isBookReservable(bookId);
        return ResponseEntity.ok(reservable);
    }
}