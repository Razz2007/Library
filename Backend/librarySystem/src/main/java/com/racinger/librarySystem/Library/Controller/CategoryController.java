package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Category;
import com.racinger.librarySystem.Library.Service.interfaces.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "📂 Categories", description = "Gestión completa de categorías de libros")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    @Operation(
        summary = "📂 Obtener todas las categorías",
        description = "Retorna una lista completa de todas las categorías registradas en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de categorías obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener categoría por ID",
        description = "Retorna la información completa de una categoría específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Categoría encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Categoría no encontrada con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Category> getCategoryById(@Parameter(description = "ID único de la categoría a buscar", example = "1") @PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nueva categoría",
        description = "Crea una nueva categoría en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Categoría creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Nombre de categoría ya existe o datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (categoryService.existsByName(category.getName())) {
            return ResponseEntity.badRequest().build();
        }
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "✏️ Actualizar categoría",
        description = "Actualiza la información de una categoría existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Categoría actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Categoría no encontrada"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Category> updateCategory(@Parameter(description = "ID de la categoría a actualizar", example = "1") @PathVariable Long id, @RequestBody Category category) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        category.setId(id);
        Category updatedCategory = categoryService.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "🗑️ Eliminar categoría",
        description = "Elimina una categoría del sistema por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Categoría eliminada exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ No se puede eliminar categoría con libros asociados"),
        @ApiResponse(responseCode = "404", description = "❌ Categoría no encontrada"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "ID de la categoría a eliminar", example = "1") @PathVariable Long id) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    @Operation(
        summary = "🔍 Buscar categoría por nombre",
        description = "Busca una categoría por su nombre exacto"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Categoría encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Categoría no encontrada con el nombre especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Category> getCategoryByName(@Parameter(description = "Nombre exacto de la categoría", example = "Ficción") @RequestParam String name) {
        Optional<Category> category = categoryService.findByName(name);
        return category.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}