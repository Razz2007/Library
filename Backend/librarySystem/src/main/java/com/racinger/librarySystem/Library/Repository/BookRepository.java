package com.racinger.librarySystem.Library.Repository;

import com.racinger.librarySystem.Library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // 🔹 Carga todos los libros junto con sus autores y categoría (evita ConcurrentModificationException)
    @Query("SELECT DISTINCT b FROM Book b " +
           "LEFT JOIN FETCH b.authors " +
           "LEFT JOIN FETCH b.category")
    List<Book> findAllWithRelations();

    // 🔹 Búsqueda flexible por título, ISBN o nombre del autor
    @Query("SELECT DISTINCT b FROM Book b " +
           "LEFT JOIN b.authors a " +
           "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "   OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(String keyword);

    // 🔹 Libros disponibles (con copias > 0)
    @Query("SELECT b FROM Book b WHERE b.availableCopies > 0")
    List<Book> findAvailableBooks();

    // 🔹 Libros por categoría
    List<Book> findByCategoryId(Long categoryId);

    // 🔹 Libros por autor
    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a WHERE a.id = :authorId")
    List<Book> findByAuthorsId(Long authorId);
}
