package com.racinger.librarySystem.Library.DTO;

import jakarta.validation.constraints.*;
import java.util.List;

public class BookDto {

    private int id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @NotNull(message = "El año de publicación es obligatorio")
    @Min(value = 1000, message = "El año de publicación debe ser mayor a 1000")
    @Max(value = 2100, message = "El año de publicación debe ser menor a 2100")
    private Integer publicationYear;

    @NotBlank(message = "El ISBN es obligatorio")
    @Size(max = 50, message = "El ISBN no puede exceder 50 caracteres")
    private String isbn;

    @NotNull(message = "El total de copias es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 copia")
    private Integer totalCopies;

    @NotNull(message = "Las copias disponibles son obligatorias")
    @Min(value = 0, message = "Las copias disponibles no pueden ser negativas")
    private Integer availableCopies;

    @NotNull(message = "El ID de categoría es obligatorio")
    private int categoryId;

    private String categoryName;

    @NotEmpty(message = "Debe haber al menos un autor")
    private List<AuthorDto> authors;

    // Constructor vacío
    public BookDto() {
    }

    // Constructor completo
    public BookDto(int id, String title, String description, Integer publicationYear, String isbn,
                   Integer totalCopies, Integer availableCopies, int categoryId, String categoryName,
                   List<AuthorDto> authors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.authors = authors;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }
}