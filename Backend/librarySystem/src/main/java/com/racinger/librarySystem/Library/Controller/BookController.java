package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.DTO.BookDto;
import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Factory.BookFactory;
import com.racinger.librarySystem.Library.Service.interfaces.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@Validated
@Tag(name = "📚 Books", description = "Gestión completa de libros, autores y categorías")
public class BookController {

    private final IBookService bookService;
    private final BookFactory bookFactory;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    // Constructor
    public BookController(IBookService bookService, BookFactory bookFactory) {
        this.bookService = bookService;
        this.bookFactory = bookFactory;
    }

    @GetMapping
    @Operation(
        summary = "📚 Obtener todos los libros",
        description = "Retorna una lista completa de todos los libros registrados en el sistema con su información detallada"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de libros obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.debug("Solicitud para obtener todos los libros");
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = books.stream()
            .map(bookFactory::createBookDto)
            .collect(Collectors.toList());
        log.info("Retornando {} libros", bookDtos.size());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener libro por ID",
        description = "Retorna la información completa de un libro específico incluyendo autores, categoría y estado de disponibilidad"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Libro encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Libro no encontrado con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<BookDto> getBookById(@Parameter(description = "ID único del libro a buscar", example = "1") @PathVariable Long id) {
        log.debug("Solicitud para obtener libro con ID: {}", id);
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            BookDto bookDto = bookFactory.createBookDto(book.get());
            log.info("Libro encontrado: {}", book.get().getTitle());
            return ResponseEntity.ok(bookDto);
        } else {
            log.warn("Libro no encontrado con ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nuevo libro",
        description = "Crea un nuevo libro en el sistema con toda su información"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Libro creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
        log.debug("Solicitud para crear nuevo libro: {}", bookDto.getTitle());
        try {
            // Aquí necesitaríamos lógica adicional para convertir DTO a entidad completa
            // Por simplicidad, asumimos que el DTO tiene toda la info necesaria
            Book book = bookFactory.createBookEntity(bookDto);
            Book savedBook = bookService.save(book);
            BookDto responseDto = bookFactory.createBookDto(savedBook);
            log.info("Libro creado exitosamente: {}", savedBook.getTitle());
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error al crear libro: {}", bookDto.getTitle(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "✏️ Actualizar libro",
        description = "Actualiza la información de un libro existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Libro actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Libro no encontrado"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<BookDto> updateBook(@Parameter(description = "ID del libro a actualizar", example = "1") @PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        log.debug("Solicitud para actualizar libro con ID: {}", id);
        if (!bookService.findById(id).isPresent()) {
            log.warn("Libro no encontrado para actualización, ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        try {
            Book book = bookFactory.createBookEntity(bookDto);
            book.setId(id);
            Book updatedBook = bookService.save(book);
            BookDto responseDto = bookFactory.createBookDto(updatedBook);
            log.info("Libro actualizado exitosamente: {}", updatedBook.getTitle());
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error al actualizar libro con ID: {}", id, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "🗑️ Eliminar libro",
        description = "Elimina un libro del sistema por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Libro eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ No se puede eliminar libro con préstamos o reservaciones activas"),
        @ApiResponse(responseCode = "404", description = "❌ Libro no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> deleteBook(@Parameter(description = "ID del libro a eliminar", example = "1") @PathVariable Long id) {
        log.debug("Solicitud para eliminar libro con ID: {}", id);
        if (!bookService.findById(id).isPresent()) {
            log.warn("Libro no encontrado para eliminación, ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        try {
            bookService.deleteById(id);
            log.info("Libro eliminado exitosamente, ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            log.warn("No se puede eliminar libro con ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error al eliminar libro con ID: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/search")
    @Operation(
        summary = "🔍 Buscar libros",
        description = "Busca libros por título, autor o ISBN"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<BookDto>> searchBooks(@Parameter(description = "Palabra clave para buscar (título, autor o ISBN)", example = "Harry Potter") @RequestParam String keyword) {
        log.debug("Solicitud de búsqueda de libros con palabra clave: {}", keyword);
        List<Book> books = bookService.searchBooks(keyword);
        List<BookDto> bookDtos = books.stream()
            .map(bookFactory::createBookDto)
            .collect(Collectors.toList());
        log.info("Búsqueda completada, encontrados {} libros", bookDtos.size());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/available")
    @Operation(
        summary = "✅ Obtener libros disponibles",
        description = "Retorna una lista de libros que están disponibles para préstamo"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de libros disponibles obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<BookDto>> getAvailableBooks() {
        log.debug("Solicitud para obtener libros disponibles");
        List<Book> books = bookService.findAvailableBooks();
        List<BookDto> bookDtos = books.stream()
            .map(bookFactory::createBookDto)
            .collect(Collectors.toList());
        log.info("Encontrados {} libros disponibles", bookDtos.size());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
        summary = "📂 Obtener libros por categoría",
        description = "Retorna todos los libros de una categoría específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de libros obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<BookDto>> getBooksByCategory(@Parameter(description = "ID de la categoría", example = "1") @PathVariable Long categoryId) {
        log.debug("Solicitud para obtener libros por categoría ID: {}", categoryId);
        List<Book> books = bookService.findBooksByCategory(categoryId);
        List<BookDto> bookDtos = books.stream()
            .map(bookFactory::createBookDto)
            .collect(Collectors.toList());
        log.info("Encontrados {} libros para la categoría {}", bookDtos.size(), categoryId);
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/author/{authorId}")
    @Operation(
        summary = "✍️ Obtener libros por autor",
        description = "Retorna todos los libros de un autor específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de libros obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@Parameter(description = "ID del autor", example = "1") @PathVariable Long authorId) {
        log.debug("Solicitud para obtener libros por autor ID: {}", authorId);
        List<Book> books = bookService.findBooksByAuthor(authorId);
        List<BookDto> bookDtos = books.stream()
            .map(bookFactory::createBookDto)
            .collect(Collectors.toList());
        log.info("Encontrados {} libros para el autor {}", bookDtos.size(), authorId);
        return ResponseEntity.ok(bookDtos);
    }
}