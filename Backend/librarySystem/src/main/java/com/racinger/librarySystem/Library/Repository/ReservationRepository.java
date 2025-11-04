package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStudentId(Long studentId);

    List<Reservation> findByBookId(Long bookId);

    List<Reservation> findByStatus(Reservation.ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.expirationDate < :currentDate AND r.status = 'ACTIVE'")
    List<Reservation> findExpiredReservations(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT r FROM Reservation r WHERE r.student.id = :studentId AND r.status = 'ACTIVE'")
    List<Reservation> findActiveReservationsByStudent(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.status = 'ACTIVE'")
    Long countActiveReservations();
}