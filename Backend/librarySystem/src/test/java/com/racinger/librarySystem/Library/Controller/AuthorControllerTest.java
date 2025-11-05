package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Service.interfaces.IAuthorService;
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

class AuthorControllerTest {

    @Mock
    private IAuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private Author sampleAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        sampleAuthor = new Author();
        sampleAuthor.setId(1L);
        sampleAuthor.setFirstName("Gabriel");
        sampleAuthor.setLastName("García Márquez");
        sampleAuthor.setBiography("Premio Nobel de Literatura");
        sampleAuthor.setNationality("Colombiano");
        sampleAuthor.setBirthYear(1927);
    }

    @Test
    void getAllAuthors_ShouldReturnListOfAuthors() {
        // Arrange
        List<Author> authors = Arrays.asList(sampleAuthor);
        when(authorService.findAll()).thenReturn(authors);

        // Act
        ResponseEntity<List<Author>> response = authorController.getAllAuthors();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(authorService, times(1)).findAll();
    }

    @Test
    void getAuthorById_WhenAuthorExists_ShouldReturnAuthor() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.of(sampleAuthor));

        // Act
        ResponseEntity<Author> response = authorController.getAuthorById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Gabriel", response.getBody().getFirstName());
        assertEquals("García Márquez", response.getBody().getLastName());
        verify(authorService, times(1)).findById(1L);
    }

    @Test
    void getAuthorById_WhenAuthorDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Author> response = authorController.getAuthorById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(authorService, times(1)).findById(1L);
    }

    @Test
    void createAuthor_ShouldReturnCreatedAuthor() {
        // Arrange
        when(authorService.save(any(Author.class))).thenReturn(sampleAuthor);

        // Act
        ResponseEntity<Author> response = authorController.createAuthor(sampleAuthor);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Gabriel", response.getBody().getFirstName());
        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    void updateAuthor_WhenAuthorExists_ShouldReturnUpdatedAuthor() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.of(sampleAuthor));
        when(authorService.save(any(Author.class))).thenReturn(sampleAuthor);

        // Act
        ResponseEntity<Author> response = authorController.updateAuthor(1L, sampleAuthor);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(authorService, times(1)).save(any(Author.class));
    }

    @Test
    void updateAuthor_WhenAuthorDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Author> response = authorController.updateAuthor(1L, sampleAuthor);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(authorService, times(1)).findById(1L);
        verify(authorService, never()).save(any(Author.class));
    }

    @Test
    void deleteAuthor_WhenAuthorExists_ShouldReturnNoContent() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.of(sampleAuthor));
        doNothing().when(authorService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = authorController.deleteAuthor(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorService, times(1)).deleteById(1L);
    }

    @Test
    void deleteAuthor_WhenAuthorDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(authorService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = authorController.deleteAuthor(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(authorService, times(1)).findById(1L);
        verify(authorService, never()).deleteById(anyLong());
    }

    @Test
    void searchAuthors_ShouldReturnMatchingAuthors() {
        // Arrange
        List<Author> authors = Arrays.asList(sampleAuthor);
        when(authorService.searchAuthors("Gabriel")).thenReturn(authors);

        // Act
        ResponseEntity<List<Author>> response = authorController.searchAuthors("Gabriel");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(authorService, times(1)).searchAuthors("Gabriel");
    }

    @Test
    void getAuthorsByNationality_ShouldReturnAuthorsByNationality() {
        // Arrange
        List<Author> authors = Arrays.asList(sampleAuthor);
        when(authorService.findAuthorsByNationality("Colombiano")).thenReturn(authors);

        // Act
        ResponseEntity<List<Author>> response = authorController.getAuthorsByNationality("Colombiano");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(authorService, times(1)).findAuthorsByNationality("Colombiano");
    }
}