package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Author;
import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Author save(Author author);

    void deleteById(Long id);

    List<Author> searchAuthors(String name);

    List<Author> findAuthorsByNationality(String nationality);

    boolean existsByName(String firstName, String lastName);
}