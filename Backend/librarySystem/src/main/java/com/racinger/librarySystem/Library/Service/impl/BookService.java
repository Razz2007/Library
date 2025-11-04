package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Repository.BookRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    // ✅ Obtiene todos los libros (solo entidades)
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    // ✅ Busca libro por ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // ✅ Guarda o actualiza un libro
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // ✅ Elimina libro por ID
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    // ✅ Busca libros por palabra clave (título, ISBN o autor)
    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword);
    }

    // ✅ Libros disponibles (copias > 0)
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    // ✅ Libros por categoría
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    // ✅ Libros por autor
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorsId(authorId);
    }

    // ✅ Verifica si un libro tiene copias disponibles
    @Override
    @Transactional(readOnly = true)
    public boolean isBookAvailable(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.isPresent() && book.get().getAvailableCopies() > 0;
    }

    // ✅ Actualiza la cantidad de copias disponibles
    @Override
    public void updateAvailableCopies(Long bookId, int change) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            int newCopies = book.getAvailableCopies() + change;
            if (newCopies >= 0) {
                book.setAvailableCopies(newCopies);
                bookRepository.save(book);
            }
        }
    }
}
