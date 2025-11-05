package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Loan;
import com.racinger.librarySystem.Library.Service.interfaces.ILoanService;
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

class LoanControllerTest {

    @Mock
    private ILoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private Loan sampleLoan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        sampleLoan = new Loan();
        sampleLoan.setId(1L);
        sampleLoan.setLoanDate(LocalDate.of(2024, 10, 1));
        sampleLoan.setDueDate(LocalDate.of(2024, 10, 15));
        sampleLoan.setStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void getAllLoans_ShouldReturnListOfLoans() {
        // Arrange
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findAll()).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getAllLoans();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findAll();
    }

    @Test
    void getLoanById_WhenLoanExists_ShouldReturnLoan() {
        // Arrange
        when(loanService.findById(1L)).thenReturn(Optional.of(sampleLoan));

        // Act
        ResponseEntity<Loan> response = loanController.getLoanById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Loan.LoanStatus.ACTIVE, response.getBody().getStatus());
        verify(loanService, times(1)).findById(1L);
    }

    @Test
    void getLoanById_WhenLoanDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(loanService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Loan> response = loanController.getLoanById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(loanService, times(1)).findById(1L);
    }

    @Test
    void createLoan_ShouldReturnCreatedLoan() {
        // Arrange
        when(loanService.createLoan(1L, 1L, LocalDate.of(2024, 10, 15))).thenReturn(sampleLoan);

        // Act
        ResponseEntity<Loan> response = loanController.createLoan(1L, 1L, LocalDate.of(2024, 10, 15));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Loan.LoanStatus.ACTIVE, response.getBody().getStatus());
        verify(loanService, times(1)).createLoan(1L, 1L, LocalDate.of(2024, 10, 15));
    }

    @Test
    void createLoan_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        when(loanService.createLoan(1L, 1L, LocalDate.of(2024, 10, 15)))
            .thenThrow(new IllegalArgumentException("Invalid loan data"));

        // Act
        ResponseEntity<Loan> response = loanController.createLoan(1L, 1L, LocalDate.of(2024, 10, 15));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(loanService, times(1)).createLoan(1L, 1L, LocalDate.of(2024, 10, 15));
    }

    @Test
    void returnBook_ShouldReturnUpdatedLoan() {
        // Arrange
        sampleLoan.setStatus(Loan.LoanStatus.RETURNED);
        sampleLoan.setReturnDate(LocalDate.of(2024, 10, 12));
        when(loanService.returnBook(1L, LocalDate.of(2024, 10, 12))).thenReturn(sampleLoan);

        // Act
        ResponseEntity<Loan> response = loanController.returnBook(1L, LocalDate.of(2024, 10, 12));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Loan.LoanStatus.RETURNED, response.getBody().getStatus());
        verify(loanService, times(1)).returnBook(1L, LocalDate.of(2024, 10, 12));
    }

    @Test
    void returnBook_WhenInvalidData_ShouldReturnBadRequest() {
        // Arrange
        when(loanService.returnBook(1L, LocalDate.of(2024, 10, 12)))
            .thenThrow(new IllegalArgumentException("Invalid return data"));

        // Act
        ResponseEntity<Loan> response = loanController.returnBook(1L, LocalDate.of(2024, 10, 12));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(loanService, times(1)).returnBook(1L, LocalDate.of(2024, 10, 12));
    }

    @Test
    void getLoansByStudent_ShouldReturnLoansByStudent() {
        // Arrange
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findLoansByStudent(1L)).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getLoansByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findLoansByStudent(1L);
    }

    @Test
    void getLoansByBook_ShouldReturnLoansByBook() {
        // Arrange
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findLoansByBook(1L)).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getLoansByBook(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findLoansByBook(1L);
    }

    @Test
    void getActiveLoansByStudent_ShouldReturnActiveLoansByStudent() {
        // Arrange
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findActiveLoansByStudent(1L)).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getActiveLoansByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findActiveLoansByStudent(1L);
    }

    @Test
    void getOverdueLoans_ShouldReturnOverdueLoans() {
        // Arrange
        sampleLoan.setStatus(Loan.LoanStatus.OVERDUE);
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findOverdueLoans()).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getOverdueLoans();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findOverdueLoans();
    }

    @Test
    void getLoansByStatus_ShouldReturnLoansByStatus() {
        // Arrange
        List<Loan> loans = Arrays.asList(sampleLoan);
        when(loanService.findLoansByStatus(Loan.LoanStatus.ACTIVE)).thenReturn(loans);

        // Act
        ResponseEntity<List<Loan>> response = loanController.getLoansByStatus(Loan.LoanStatus.ACTIVE);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(loanService, times(1)).findLoansByStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void checkBookAvailability_ShouldReturnAvailability() {
        // Arrange
        when(loanService.isBookAvailableForLoan(1L)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = loanController.checkBookAvailability(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
        verify(loanService, times(1)).isBookAvailableForLoan(1L);
    }

    @Test
    void checkStudentLoanLimit_ShouldReturnLoanLimitStatus() {
        // Arrange
        when(loanService.canStudentBorrow(1L)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = loanController.checkStudentLoanLimit(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody());
        verify(loanService, times(1)).canStudentBorrow(1L);
    }

    @Test
    void getActiveLoanCountByStudent_ShouldReturnActiveLoanCount() {
        // Arrange
        when(loanService.getActiveLoansCountByStudent(1L)).thenReturn(2);

        // Act
        ResponseEntity<Integer> response = loanController.getActiveLoanCountByStudent(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody());
        verify(loanService, times(1)).getActiveLoansCountByStudent(1L);
    }
}