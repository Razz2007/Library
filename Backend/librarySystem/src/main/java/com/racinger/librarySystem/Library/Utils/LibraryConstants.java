package com.racinger.librarySystem.Library.Utils;

public class LibraryConstants {

    // Loan limits
    public static final int MAX_LOANS_PER_STUDENT = 3;
    public static final int DEFAULT_LOAN_DURATION_DAYS = 14;

    // Reservation limits
    public static final int MAX_RESERVATIONS_PER_STUDENT = 2;
    public static final int RESERVATION_DURATION_DAYS = 7;

    // Penalty rates
    public static final double PENALTY_PER_DAY = 1.0;

    // Database table names
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_AUTHORS = "authors";
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_LOANS = "loans";
    public static final String TABLE_RETURNS = "returns";
    public static final String TABLE_PENALTIES = "penalties";
    public static final String TABLE_RESERVATIONS = "reservations";
    public static final String TABLE_BOOK_AUTHORS = "book_authors";
    public static final String TABLE_STUDENT_BOOKS = "student_books";

    // Status values
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_RETURNED = "RETURNED";
    public static final String STATUS_OVERDUE = "OVERDUE";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_PAID = "PAID";
    public static final String STATUS_CANCELLED = "CANCELLED";
    public static final String STATUS_EXPIRED = "EXPIRED";
    public static final String STATUS_FULFILLED = "FULFILLED";

    // Error messages
    public static final String ERROR_STUDENT_NOT_FOUND = "Estudiante no encontrado";
    public static final String ERROR_BOOK_NOT_FOUND = "Libro no encontrado";
    public static final String ERROR_LOAN_NOT_FOUND = "Préstamo no encontrado";
    public static final String ERROR_BOOK_NOT_AVAILABLE = "El libro no está disponible";
    public static final String ERROR_STUDENT_INACTIVE = "El estudiante no está activo";
    public static final String ERROR_LOAN_LIMIT_EXCEEDED = "El estudiante ha alcanzado el límite de préstamos";
    public static final String ERROR_RESERVATION_LIMIT_EXCEEDED = "El estudiante ha alcanzado el límite de reservas";
    public static final String ERROR_LOAN_NOT_ACTIVE = "El préstamo no está activo";
    public static final String ERROR_PENALTY_NOT_PENDING = "La penalización no está pendiente";

    // Success messages
    public static final String SUCCESS_LOAN_CREATED = "Préstamo creado exitosamente";
    public static final String SUCCESS_BOOK_RETURNED = "Libro devuelto exitosamente";
    public static final String SUCCESS_RESERVATION_CREATED = "Reserva creada exitosamente";
    public static final String SUCCESS_PENALTY_PAID = "Penalización pagada exitosamente";

    private LibraryConstants() {
        // Utility class
    }
}