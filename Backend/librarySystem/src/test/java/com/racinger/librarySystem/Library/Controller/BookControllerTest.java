package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.DTO.AuthorDto;
import com.racinger.librarySystem.Library.DTO.BookDto;
import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Factory.BookFactory;
import com.racinger.librarySystem.Library.Service.interfaces.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private IBookService bookService;

    @Mock
    private BookFactory bookFactory;

    @InjectMocks
    private BookController bookController;

    private BookDto sampleBookDto;
    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1L);
        authorDto.setFirstName("Gabriel");
        authorDto.setLastName("García Márquez");

        sampleBookDto = new BookDto();
        sampleBookDto.setId(1);
        sampleBookDto.setTitle("Cien años de soledad");
        sampleBookDto.setDescription("Novela magistral");
        sampleBookDto.setPublicationYear(1967);
        sampleBookDto.setIsbn("978-84-376-0494-7");
        sampleBookDto.setTotalCopies(5);
        sampleBookDto.setAvailableCopies(5);
        sampleBookDto.setCategoryId(1);
        sampleBookDto.setCategoryName("Ficción");
        sampleBookDto.setAuthors(Arrays.asList(authorDto));

        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Cien años de soledad");
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        // Arrange
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findAll()).thenReturn(books);
        when(bookFactory.createBookDto(any(Book.class))).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<List<BookDto>> response = bookController.getAllBooks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookFactory.createBookDto(sampleBook)).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<BookDto> response = bookController.getBookById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Cien años de soledad", response.getBody().getTitle());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    void getBookById_WhenBookDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<BookDto> response = bookController.getBookById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    void createBook_ShouldReturnCreatedBook() {
        // Arrange
        when(bookFactory.createBookEntity(sampleBookDto)).thenReturn(sampleBook);
        when(bookService.save(sampleBook)).thenReturn(sampleBook);
        when(bookFactory.createBookDto(sampleBook)).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<BookDto> response = bookController.createBook(sampleBookDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Cien años de soledad", response.getBody().getTitle());
        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    void updateBook_WhenBookExists_ShouldReturnUpdatedBook() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookFactory.createBookEntity(sampleBookDto)).thenReturn(sampleBook);
        when(bookService.save(any(Book.class))).thenReturn(sampleBook);
        when(bookFactory.createBookDto(sampleBook)).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<BookDto> response = bookController.updateBook(1L, sampleBookDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    void updateBook_WhenBookDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<BookDto> response = bookController.updateBook(1L, sampleBookDto);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, never()).save(any(Book.class));
    }

    @Test
    void deleteBook_WhenBookExists_ShouldReturnNoContent() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.of(sampleBook));
        doNothing().when(bookService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    void deleteBook_WhenBookDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(bookService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, never()).deleteById(anyLong());
    }

    @Test
    void searchBooks_ShouldReturnMatchingBooks() {
        // Arrange
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.searchBooks("soledad")).thenReturn(books);
        when(bookFactory.createBookDto(any(Book.class))).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<List<BookDto>> response = bookController.searchBooks("soledad");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).searchBooks("soledad");
    }

    @Test
    void getAvailableBooks_ShouldReturnAvailableBooks() {
        // Arrange
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findAvailableBooks()).thenReturn(books);
        when(bookFactory.createBookDto(any(Book.class))).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<List<BookDto>> response = bookController.getAvailableBooks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).findAvailableBooks();
    }

    @Test
    void getBooksByCategory_ShouldReturnBooksInCategory() {
        // Arrange
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findBooksByCategory(1L)).thenReturn(books);
        when(bookFactory.createBookDto(any(Book.class))).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<List<BookDto>> response = bookController.getBooksByCategory(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).findBooksByCategory(1L);
    }

    @Test
    void getBooksByAuthor_ShouldReturnBooksByAuthor() {
        // Arrange
        List<Book> books = Arrays.asList(sampleBook);
        when(bookService.findBooksByAuthor(1L)).thenReturn(books);
        when(bookFactory.createBookDto(any(Book.class))).thenReturn(sampleBookDto);

        // Act
        ResponseEntity<List<BookDto>> response = bookController.getBooksByAuthor(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(bookService, times(1)).findBooksByAuthor(1L);
    }
}