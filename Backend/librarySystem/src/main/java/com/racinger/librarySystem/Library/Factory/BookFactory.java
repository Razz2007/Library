package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.AuthorDto;
import com.racinger.librarySystem.Library.DTO.BookDto;
import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookFactory {

    public BookDto createBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setIsbn(book.getIsbn());
        dto.setTotalCopies(book.getTotalCopies());
        dto.setAvailableCopies(book.getAvailableCopies());

        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId());
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
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPublicationYear(dto.getPublicationYear());
        book.setIsbn(dto.getIsbn());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        return book;
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
