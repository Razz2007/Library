package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Penalty;
import com.racinger.librarySystem.Library.Service.interfaces.IPenaltyService;
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

class PenaltyControllerTest {

    @Mock
    private IPenaltyService penaltyService;

    @InjectMocks
    private PenaltyController penaltyController;

    private Penalty samplePenalty;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        samplePenalty = new Penalty();
        samplePenalty.setId(1L);
        samplePenalty.setAmount(5000.0);
        samplePenalty.setReason("Devolución tardía");
        samplePenalty.setIssuedDate(LocalDate.of(2024, 10, 25));
        samplePenalty.setStatus(Penalty.PenaltyStatus.PENDING);
    }

    @Test
    void getAllPenalties_ShouldReturnListOfPenalties() {
        // Arrange
        List<Penalty> penalties = Arrays.asList(samplePenalty);
        when(penaltyService.findAll()).thenReturn(penalties);

        // Act
        ResponseEntity<List<Penalty>> response = penaltyController.getAllPenalties();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(penaltyService, times(1)).findAll();
    }

    @Test
    void getPenaltyById_WhenPenaltyExists_ShouldReturnPenalty() {
        // Arrange
        when(penaltyService.findById(1L)).thenReturn(Optional.of(samplePenalty));

        // Act
        ResponseEntity<Penalty> response = penaltyController.getPenaltyById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Devolución tardía", response.getBody().getReason());
        verify(penaltyService, times(1)).findById(1L);
    }

    @Test
    void getPenaltyById_WhenPenaltyDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(penaltyService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Penalty> response = penaltyController.getPenaltyById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(penaltyService, times(1)).findById(1L);
    }

    @Test
    void createPenalty_ShouldReturnCreatedPenalty() {
        // Arrange
        when(penaltyService.createPenalty(1L, 5000.0, "Devolución tardía")).thenReturn(samplePenalty);

        // Act
        ResponseEntity<Penalty> response = penaltyController.createPenalty(1L, 5000.0, "Devolución tardía");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5000.0, response.getBody().getAmount());
        verify(penaltyService, times(1)).createPenalty(1L, 5000.0, "Devolución tardía");
    }

    @Test
    void createPenalty_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        when(penaltyService.createPenalty(1L, 5000.0, "Devolución tardía"))
            .thenThrow(new IllegalArgumentException("Invalid penalty data"));

        // Act
        ResponseEntity<Penalty> response = penaltyController.createPenalty(1L, 5000.0, "Devolución tardía");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(penaltyService, times(1)).createPenalty(1L, 5000.0, "Devolución tardía");
    }

    @Test
    void payPenalty_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(penaltyService).payPenalty(1L);

        // Act
        ResponseEntity<Void> response = penaltyController.payPenalty(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(penaltyService, times(1)).payPenalty(1L);
    }

    @Test
    void payPenalty_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid penalty payment")).when(penaltyService).payPenalty(1L);

        // Act
        ResponseEntity<Void> response = penaltyController.payPenalty(1L);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(penaltyService, times(1)).payPenalty(1L);
    }

    @Test
    void getPenaltiesByStudent_ShouldReturnPenaltiesByStudent() {
        // Arrange
        List<Penalty> penalties = Arrays.asList(samplePenalty);
        when(penaltyService.findPenaltiesByStudent(1L)).thenReturn(penalties);

        // Act
        ResponseEntity<List<Penalty>> response = penaltyController.getPenaltiesByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(penaltyService, times(1)).findPenaltiesByStudent(1L);
    }

    @Test
    void getPenaltiesByStatus_ShouldReturnPenaltiesByStatus() {
        // Arrange
        List<Penalty> penalties = Arrays.asList(samplePenalty);
        when(penaltyService.findPenaltiesByStatus(Penalty.PenaltyStatus.PENDING)).thenReturn(penalties);

        // Act
        ResponseEntity<List<Penalty>> response = penaltyController.getPenaltiesByStatus(Penalty.PenaltyStatus.PENDING);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(penaltyService, times(1)).findPenaltiesByStatus(Penalty.PenaltyStatus.PENDING);
    }

    @Test
    void getPenaltiesInDateRange_ShouldReturnPenaltiesInDateRange() {
        // Arrange
        List<Penalty> penalties = Arrays.asList(samplePenalty);
        LocalDate startDate = LocalDate.of(2024, 10, 1);
        LocalDate endDate = LocalDate.of(2024, 10, 31);
        when(penaltyService.findPenaltiesInDateRange(startDate, endDate)).thenReturn(penalties);

        // Act
        ResponseEntity<List<Penalty>> response = penaltyController.getPenaltiesInDateRange(startDate, endDate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(penaltyService, times(1)).findPenaltiesInDateRange(startDate, endDate);
    }

    @Test
    void getTotalPendingPenaltiesByStudent_ShouldReturnTotalPendingPenalties() {
        // Arrange
        when(penaltyService.getTotalPendingPenaltiesByStudent(1L)).thenReturn(5000.0);

        // Act
        ResponseEntity<Double> response = penaltyController.getTotalPendingPenaltiesByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5000.0, response.getBody());
        verify(penaltyService, times(1)).getTotalPendingPenaltiesByStudent(1L);
    }

    @Test
    void hasPendingPenalties_ShouldReturnPendingPenaltiesStatus() {
        // Arrange
        when(penaltyService.hasPendingPenalties(1L)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = penaltyController.hasPendingPenalties(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
        verify(penaltyService, times(1)).hasPendingPenalties(1L);
    }
}