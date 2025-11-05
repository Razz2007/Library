package com.racinger.librarySystem.Library.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "books", uniqueConstraints = {
    @UniqueConstraint(columnNames = "isbn")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    private String title;

    @Column(length = 500)
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "El año de publicación es obligatorio")
    @Min(value = 1000, message = "El año de publicación debe ser mayor a 1000")
    @Max(value = 2100, message = "El año de publicación debe ser menor a 2100")
    private Integer publicationYear;

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 50, message = "El ISBN no puede exceder 50 caracteres")
    private String isbn;

    @Column(nullable = false)
    @NotNull(message = "El total de copias es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 copia")
    private Integer totalCopies;

    @Column(nullable = false)
    @NotNull(message = "Las copias disponibles son obligatorias")
    @Min(value = 0, message = "Las copias disponibles no pueden ser negativas")
    private Integer availableCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "La categoría es obligatoria")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @NotEmpty(message = "Debe haber al menos un autor")
    private Set<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Loan> loans;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    // Constructor vacío
    public Book() {
    }

    // Constructor completo
    public Book(Long id, String title, String description, Integer publicationYear, String isbn,
                Integer totalCopies, Integer availableCopies, Category category, Set<Author> authors,
                Set<Loan> loans, Set<Reservation> reservations) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.category = category;
        this.authors = authors;
        this.loans = loans;
        this.reservations = reservations;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}