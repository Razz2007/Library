package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Repository.BookRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    // Constructor
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ✅ Obtiene todos los libros (solo entidades)
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        log.debug("Obteniendo todos los libros");
        List<Book> books = bookRepository.findAll();
        log.info("Se encontraron {} libros", books.size());
        return books;
    }

    // ✅ Busca libro por ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        log.debug("Buscando libro con ID: {}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            log.info("Libro encontrado: {}", book.get().getTitle());
        } else {
            log.warn("Libro con ID {} no encontrado", id);
        }
        return book;
    }

    // ✅ Guarda o actualiza un libro
    @Override
    public Book save(Book book) {
        log.debug("Guardando libro: {}", book.getTitle());
        try {
            Book savedBook = bookRepository.save(book);
            log.info("Libro guardado exitosamente con ID: {}", savedBook.getId());
            return savedBook;
        } catch (Exception e) {
            log.error("Error al guardar el libro: {}", book.getTitle(), e);
            throw new RuntimeException("Error al guardar el libro", e);
        }
    }

    // ✅ Elimina libro por ID
    @Override
    public void deleteById(Long id) {
        log.debug("Eliminando libro con ID: {}", id);

        // Verificar si el libro tiene préstamos activos
        if (bookRepository.hasActiveLoans(id)) {
            log.warn("Intento de eliminar libro con préstamos activos, ID: {}", id);
            throw new IllegalStateException("Cannot delete book with active loans. Return all loaned copies first.");
        }

        // Verificar si el libro tiene reservaciones activas
        if (bookRepository.hasActiveReservations(id)) {
            log.warn("Intento de eliminar libro con reservaciones activas, ID: {}", id);
            throw new IllegalStateException("Cannot delete book with active reservations. Cancel reservations first.");
        }

        try {
            if (bookRepository.existsById(id)) {
                bookRepository.deleteById(id);
                log.info("Libro con ID {} eliminado exitosamente", id);
            } else {
                log.warn("Intento de eliminar libro inexistente con ID: {}", id);
                throw new IllegalArgumentException("Libro no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error al eliminar el libro con ID: {}", id, e);
            throw new RuntimeException("Error al eliminar el libro", e);
        }
    }

    // ✅ Busca libros por palabra clave (título, ISBN o autor)
    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooks(String keyword) {
        log.debug("Buscando libros con palabra clave: {}", keyword);
        if (keyword == null || keyword.trim().isEmpty()) {
            log.warn("Palabra clave de búsqueda vacía o nula");
            return List.of();
        }
        List<Book> books = bookRepository.searchBooks(keyword.trim());
        log.info("Encontrados {} libros para la búsqueda: {}", books.size(), keyword);
        return books;
    }

    // ✅ Libros disponibles (copias > 0)
    @Override
    @Transactional(readOnly = true)
    public List<Book> findAvailableBooks() {
        log.debug("Obteniendo libros disponibles");
        List<Book> books = bookRepository.findAvailableBooks();
        log.info("Encontrados {} libros disponibles", books.size());
        return books;
    }

    // ✅ Libros por categoría
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByCategory(Long categoryId) {
        log.debug("Buscando libros por categoría ID: {}", categoryId);
        if (categoryId == null) {
            log.warn("ID de categoría nulo");
            return List.of();
        }
        List<Book> books = bookRepository.findByCategoryId(categoryId);
        log.info("Encontrados {} libros para la categoría {}", books.size(), categoryId);
        return books;
    }

    // ✅ Libros por autor
    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Long authorId) {
        log.debug("Buscando libros por autor ID: {}", authorId);
        if (authorId == null) {
            log.warn("ID de autor nulo");
            return List.of();
        }
        List<Book> books = bookRepository.findByAuthorsId(authorId);
        log.info("Encontrados {} libros para el autor {}", books.size(), authorId);
        return books;
    }

    // ✅ Verifica si un libro tiene copias disponibles
    @Override
    @Transactional(readOnly = true)
    public boolean isBookAvailable(Long bookId) {
        log.debug("Verificando disponibilidad del libro ID: {}", bookId);
        if (bookId == null) {
            log.warn("ID de libro nulo para verificación de disponibilidad");
            return false;
        }
        return bookRepository.findById(bookId)
            .map(book -> {
                boolean available = book.getAvailableCopies() > 0;
                log.debug("Libro ID {} disponible: {}", bookId, available);
                return available;
            })
            .orElseGet(() -> {
                log.warn("Libro con ID {} no encontrado para verificación de disponibilidad", bookId);
                return false;
            });
    }

    // ✅ Actualiza la cantidad de copias disponibles
    @Override
    public void updateAvailableCopies(Long bookId, int change) {
        log.debug("Actualizando copias disponibles para libro ID: {} con cambio: {}", bookId, change);
        if (bookId == null) {
            log.error("ID de libro nulo para actualización de copias");
            throw new IllegalArgumentException("ID de libro no puede ser nulo");
        }

        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> {
                log.error("Libro no encontrado para actualización de copias, ID: {}", bookId);
                return new IllegalArgumentException("Libro no encontrado con ID: " + bookId);
            });

        int currentCopies = book.getAvailableCopies();
        int newCopies = currentCopies + change;

        if (newCopies < 0) {
            log.error("Intento de establecer copias negativas para libro ID: {} (actual: {}, cambio: {})", bookId, currentCopies, change);
            throw new IllegalArgumentException("Las copias disponibles no pueden ser negativas");
        }

        book.setAvailableCopies(newCopies);
        bookRepository.save(book);
        log.info("Copias disponibles actualizadas para libro '{}' (ID: {}): {} -> {}",
            book.getTitle(), bookId, currentCopies, newCopies);
    }
}
