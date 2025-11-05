package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Category;
import com.racinger.librarySystem.Library.Service.interfaces.ICategoryService;
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

class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private Category sampleCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear datos de prueba
        sampleCategory = new Category();
        sampleCategory.setId(1L);
        sampleCategory.setName("Ficción");
        sampleCategory.setDescription("Libros de ficción y literatura narrativa");
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        // Arrange
        List<Category> categories = Arrays.asList(sampleCategory);
        when(categoryService.findAll()).thenReturn(categories);

        // Act
        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(categoryService, times(1)).findAll();
    }

    @Test
    void getCategoryById_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.of(sampleCategory));

        // Act
        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ficción", response.getBody().getName());
        verify(categoryService, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoryService, times(1)).findById(1L);
    }

    @Test
    void createCategory_WhenCategoryDoesNotExist_ShouldReturnCreatedCategory() {
        // Arrange
        when(categoryService.existsByName("Ficción")).thenReturn(false);
        when(categoryService.save(any(Category.class))).thenReturn(sampleCategory);

        // Act
        ResponseEntity<Category> response = categoryController.createCategory(sampleCategory);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ficción", response.getBody().getName());
        verify(categoryService, times(1)).existsByName("Ficción");
        verify(categoryService, times(1)).save(any(Category.class));
    }

    @Test
    void createCategory_WhenCategoryExists_ShouldReturnBadRequest() {
        // Arrange
        when(categoryService.existsByName("Ficción")).thenReturn(true);

        // Act
        ResponseEntity<Category> response = categoryController.createCategory(sampleCategory);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoryService, times(1)).existsByName("Ficción");
        verify(categoryService, never()).save(any(Category.class));
    }

    @Test
    void updateCategory_WhenCategoryExists_ShouldReturnUpdatedCategory() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.of(sampleCategory));
        when(categoryService.save(any(Category.class))).thenReturn(sampleCategory);

        // Act
        ResponseEntity<Category> response = categoryController.updateCategory(1L, sampleCategory);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(categoryService, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Category> response = categoryController.updateCategory(1L, sampleCategory);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoryService, times(1)).findById(1L);
        verify(categoryService, never()).save(any(Category.class));
    }

    @Test
    void deleteCategory_WhenCategoryExists_ShouldReturnNoContent() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.of(sampleCategory));
        doNothing().when(categoryService).deleteById(1L);

        // Act
        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteById(1L);
    }

    @Test
    void deleteCategory_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(categoryService.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).findById(1L);
        verify(categoryService, never()).deleteById(anyLong());
    }

    @Test
    void getCategoryByName_WhenCategoryExists_ShouldReturnCategory() {
        // Arrange
        when(categoryService.findByName("Ficción")).thenReturn(Optional.of(sampleCategory));

        // Act
        ResponseEntity<Category> response = categoryController.getCategoryByName("Ficción");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ficción", response.getBody().getName());
        verify(categoryService, times(1)).findByName("Ficción");
    }

    @Test
    void getCategoryByName_WhenCategoryDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(categoryService.findByName("Ficción")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Category> response = categoryController.getCategoryByName("Ficción");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(categoryService, times(1)).findByName("Ficción");
    }
}