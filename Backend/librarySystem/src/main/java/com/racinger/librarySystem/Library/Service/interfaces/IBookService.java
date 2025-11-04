package com.racinger.librarySystem.Library.Service.interfaces;

import com.racinger.librarySystem.Library.Entity.Book;
import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    List<Book> searchBooks(String keyword);

    List<Book> findAvailableBooks();

    List<Book> findBooksByCategory(Long categoryId);

    List<Book> findBooksByAuthor(Long authorId);

    boolean isBookAvailable(Long bookId);

    void updateAvailableCopies(Long bookId, int change);
}