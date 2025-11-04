package com.racinger.librarySystem.Library.Factory;

import com.racinger.librarySystem.Library.DTO.AuthorDto;
import com.racinger.librarySystem.Library.Entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorFactory {

    public AuthorDto createAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBiography(author.getBiography());
        dto.setNationality(author.getNationality());
        dto.setBirthYear(author.getBirthYear());
        return dto;
    }

    public Author createAuthorEntity(AuthorDto dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        author.setBiography(dto.getBiography());
        author.setNationality(dto.getNationality());
        author.setBirthYear(dto.getBirthYear());
        return author;
    }
}