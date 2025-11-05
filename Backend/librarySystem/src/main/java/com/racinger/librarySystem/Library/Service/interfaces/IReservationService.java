package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Reservation;
import java.util.List;
import java.util.Optional;

public interface IReservationService {

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Reservation save(Reservation reservation);

    void deleteById(Long id);

    List<Reservation> findReservationsByStudent(Long studentId);

    List<Reservation> findReservationsByBook(Long bookId);

    List<Reservation> findActiveReservationsByStudent(Long studentId);

    List<Reservation> findExpiredReservations();

    List<Reservation> findReservationsByStatus(Reservation.ReservationStatus status);

    Reservation createReservation(Long studentId, Long bookId);

    void cancelReservation(Long reservationId);

    void fulfillReservation(Long reservationId);

    boolean canStudentReserve(Long studentId);

    boolean isBookReservable(Long bookId);
}