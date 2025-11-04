package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Entity.Reservation;
import com.racinger.librarySystem.Library.Entity.Student;
import com.racinger.librarySystem.Library.Repository.BookRepository;
import com.racinger.librarySystem.Library.Repository.ReservationRepository;
import com.racinger.librarySystem.Library.Repository.StudentRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    private static final int RESERVATION_DURATION_DAYS = 7;
    private static final int MAX_RESERVATIONS_PER_STUDENT = 2;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByStudent(Long studentId) {
        return reservationRepository.findByStudentId(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByBook(Long bookId) {
        return reservationRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findActiveReservationsByStudent(Long studentId) {
        return reservationRepository.findActiveReservationsByStudent(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findExpiredReservations() {
        return reservationRepository.findExpiredReservations(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByStatus(Reservation.ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public Reservation createReservation(Long studentId, Long bookId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (studentOpt.isEmpty() || bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Student or Book not found");
        }

        Student student = studentOpt.get();
        Book book = bookOpt.get();

        if (!student.getIsActive()) {
            throw new IllegalArgumentException("Student is not active");
        }

        if (!canStudentReserve(studentId)) {
            throw new IllegalArgumentException("Student has reached maximum reservation limit");
        }

        if (!isBookReservable(bookId)) {
            throw new IllegalArgumentException("Book is not available for reservation");
        }

        Reservation reservation = new Reservation();
        reservation.setStudent(student);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDate.now());
        reservation.setExpirationDate(LocalDate.now().plusDays(RESERVATION_DURATION_DAYS));
        reservation.setStatus(Reservation.ReservationStatus.ACTIVE);

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }

        Reservation reservation = reservationOpt.get();
        if (reservation.getStatus() != Reservation.ReservationStatus.ACTIVE) {
            throw new IllegalArgumentException("Reservation is not active");
        }

        reservation.setStatus(Reservation.ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    @Override
    public void fulfillReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }

        Reservation reservation = reservationOpt.get();
        if (reservation.getStatus() != Reservation.ReservationStatus.ACTIVE) {
            throw new IllegalArgumentException("Reservation is not active");
        }

        reservation.setStatus(Reservation.ReservationStatus.FULFILLED);
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canStudentReserve(Long studentId) {
        return findActiveReservationsByStudent(studentId).size() < MAX_RESERVATIONS_PER_STUDENT;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookReservable(Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        return bookOpt.isPresent() && bookOpt.get().getAvailableCopies() == 0;
    }
}