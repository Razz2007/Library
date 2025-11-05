package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Reservation;
import com.racinger.librarySystem.Library.Service.interfaces.IReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private IReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation sampleReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        sampleReservation = new Reservation();
        sampleReservation.setId(1L);
        sampleReservation.setReservationDate(LocalDate.of(2024, 10, 28));
        sampleReservation.setExpirationDate(LocalDate.of(2024, 11, 11));
        sampleReservation.setStatus(Reservation.ReservationStatus.ACTIVE);
    }

    @Test
    void getAllReservations_ShouldReturnListOfReservations() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findAll()).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getAllReservations();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findAll();
    }

    @Test
    void getReservationById_WhenReservationExists_ShouldReturnReservation() {
        // Arrange
        when(reservationService.findById(1L)).thenReturn(Optional.of(sampleReservation));

        // Act
        ResponseEntity<Reservation> response = reservationController.getReservationById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Reservation.ReservationStatus.ACTIVE, response.getBody().getStatus());
        verify(reservationService, times(1)).findById(1L);
    }

    @Test
    void getReservationById_WhenReservationDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(reservationService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Reservation> response = reservationController.getReservationById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(reservationService, times(1)).findById(1L);
    }

    @Test
    void createReservation_ShouldReturnCreatedReservation() {
        // Arrange
        when(reservationService.createReservation(1L, 1L)).thenReturn(sampleReservation);

        // Act
        ResponseEntity<Reservation> response = reservationController.createReservation(1L, 1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Reservation.ReservationStatus.ACTIVE, response.getBody().getStatus());
        verify(reservationService, times(1)).createReservation(1L, 1L);
    }

    @Test
    void createReservation_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        when(reservationService.createReservation(1L, 1L))
            .thenThrow(new IllegalArgumentException("Invalid reservation data"));

        // Act
        ResponseEntity<Reservation> response = reservationController.createReservation(1L, 1L);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(reservationService, times(1)).createReservation(1L, 1L);
    }

    @Test
    void cancelReservation_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(reservationService).cancelReservation(1L);

        // Act
        ResponseEntity<Void> response = reservationController.cancelReservation(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reservationService, times(1)).cancelReservation(1L);
    }

    @Test
    void cancelReservation_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid reservation cancellation")).when(reservationService).cancelReservation(1L);

        // Act
        ResponseEntity<Void> response = reservationController.cancelReservation(1L);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(reservationService, times(1)).cancelReservation(1L);
    }

    @Test
    void fulfillReservation_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(reservationService).fulfillReservation(1L);

        // Act
        ResponseEntity<Void> response = reservationController.fulfillReservation(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(reservationService, times(1)).fulfillReservation(1L);
    }

    @Test
    void fulfillReservation_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid reservation fulfillment")).when(reservationService).fulfillReservation(1L);

        // Act
        ResponseEntity<Void> response = reservationController.fulfillReservation(1L);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(reservationService, times(1)).fulfillReservation(1L);
    }

    @Test
    void getReservationsByStudent_ShouldReturnReservationsByStudent() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findReservationsByStudent(1L)).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getReservationsByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findReservationsByStudent(1L);
    }

    @Test
    void getReservationsByBook_ShouldReturnReservationsByBook() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findReservationsByBook(1L)).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getReservationsByBook(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findReservationsByBook(1L);
    }

    @Test
    void getActiveReservationsByStudent_ShouldReturnActiveReservationsByStudent() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findActiveReservationsByStudent(1L)).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getActiveReservationsByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findActiveReservationsByStudent(1L);
    }

    @Test
    void getExpiredReservations_ShouldReturnExpiredReservations() {
        // Arrange
        sampleReservation.setStatus(Reservation.ReservationStatus.EXPIRED);
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findExpiredReservations()).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getExpiredReservations();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findExpiredReservations();
    }

    @Test
    void getReservationsByStatus_ShouldReturnReservationsByStatus() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(sampleReservation);
        when(reservationService.findReservationsByStatus(Reservation.ReservationStatus.ACTIVE)).thenReturn(reservations);

        // Act
        ResponseEntity<List<Reservation>> response = reservationController.getReservationsByStatus(Reservation.ReservationStatus.ACTIVE);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(reservationService, times(1)).findReservationsByStatus(Reservation.ReservationStatus.ACTIVE);
    }

    @Test
    void checkStudentReservationLimit_ShouldReturnReservationLimitStatus() {
        // Arrange
        when(reservationService.canStudentReserve(1L)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = reservationController.checkStudentReservationLimit(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
        verify(reservationService, times(1)).canStudentReserve(1L);
    }

    @Test
    void checkBookReservable_ShouldReturnBookReservableStatus() {
        // Arrange
        when(reservationService.isBookReservable(1L)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = reservationController.checkBookReservable(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
        verify(reservationService, times(1)).isBookReservable(1L);
    }
}