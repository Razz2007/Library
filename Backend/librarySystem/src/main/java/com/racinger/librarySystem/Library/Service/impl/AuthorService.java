package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Repository.AuthorRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        // Check if author has associated books
        if (authorRepository.hasAssociatedBooks(id)) {
            throw new IllegalStateException("Cannot delete author with associated books. Remove book associations first.");
        }
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> searchAuthors(String name) {
        return authorRepository.searchAuthors(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAuthorsByNationality(String nationality) {
        return authorRepository.findByNationality(nationality);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String firstName, String lastName) {
        List<Author> authors = authorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName, lastName);
        return authors.stream().anyMatch(author ->
            author.getFirstName().equalsIgnoreCase(firstName) &&
            author.getLastName().equalsIgnoreCase(lastName));
    }
}