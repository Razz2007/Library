package com.racinger.librarySystem.Library.DTO;

import java.time.LocalDate;

public class ReservationDto {

    private Long id;
    private Long studentId;
    private String studentName;
    private Long bookId;
    private String bookTitle;
    private LocalDate reservationDate;
    private LocalDate expirationDate;
    private String status;

    public ReservationDto() {}

    public ReservationDto(Long id, Long studentId, String studentName, Long bookId, String bookTitle, LocalDate reservationDate, LocalDate expirationDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.reservationDate = reservationDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}