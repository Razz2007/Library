package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.AuthorDto;
import com.racinger.librarySystem.Library.DTO.BookDto;
import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookFactory {

    private static final Logger log = LoggerFactory.getLogger(BookFactory.class);

    public BookDto createBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId().intValue());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setIsbn(book.getIsbn());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());

        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId().intValue());
            dto.setCategoryName(book.getCategory().getName());
        }

        if (book.getAuthors() != null) {
            dto.setAuthors(
                book.getAuthors().stream()
                    .map(this::createAuthorDto)
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public Book createBookEntity(BookDto dto) {
        log.debug("Creando entidad Book desde DTO: {}", dto.getTitle());
        Book book = new Book();
        book.setId((long) dto.getId());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublicationYear(dto.getPublicationYear());
        book.setIsbn(dto.getIsbn());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());

        // Nota: Para una implementación completa, necesitaríamos servicios para obtener Category y Authors
        // Por ahora, asumimos que se manejarán en el servicio
        Category category = new Category();
        category.setId((long) dto.getCategoryId());
        book.setCategory(category);

        if (dto.getAuthors() != null && !dto.getAuthors().isEmpty()) {
            Set<Author> authors = dto.getAuthors().stream()
                .map(this::createAuthorEntity)
                .collect(Collectors.toSet());
            book.setAuthors(authors);
        }

        log.debug("Entidad Book creada exitosamente");
        return book;
    }

    private Author createAuthorEntity(AuthorDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        author.setNationality(dto.getNationality());
        author.setBirthYear(dto.getBirthYear());
        return author;
    }

    private AuthorDto createAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBiography(author.getBiography());
        dto.setNationality(author.getNationality());
        dto.setBirthYear(author.getBirthYear());
        return dto;
    }
}
