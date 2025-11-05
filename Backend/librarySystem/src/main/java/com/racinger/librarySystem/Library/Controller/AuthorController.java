package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Service.interfaces.IAuthorService;
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
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "✍️ Authors", description = "Gestión completa de autores")
public class AuthorController {

    private final IAuthorService authorService;

    @GetMapping
    @Operation(
        summary = "✍️ Obtener todos los autores",
        description = "Retorna una lista completa de todos los autores registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de autores obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "🔍 Obtener autor por ID",
        description = "Retorna la información completa de un autor específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Autor encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Autor no encontrado con el ID especificado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Author> getAuthorById(@Parameter(description = "ID único del autor a buscar", example = "1") @PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
        summary = "➕ Crear nuevo autor",
        description = "Crea un nuevo autor en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Autor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author savedAuthor = authorService.save(author);
        return ResponseEntity.ok(savedAuthor);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "✏️ Actualizar autor",
        description = "Actualiza la información de un autor existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Autor actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "❌ Autor no encontrado"),
        @ApiResponse(responseCode = "400", description = "❌ Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Author> updateAuthor(@Parameter(description = "ID del autor a actualizar", example = "1") @PathVariable Long id, @RequestBody Author author) {
        if (!authorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            author.setId(id);
            Author updatedAuthor = authorService.save(author);
            return ResponseEntity.ok(updatedAuthor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "🗑️ Eliminar autor",
        description = "Elimina un autor del sistema por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "✅ Autor eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "❌ No se puede eliminar autor con libros asociados"),
        @ApiResponse(responseCode = "404", description = "❌ Autor no encontrado"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<Void> deleteAuthor(@Parameter(description = "ID del autor a eliminar", example = "1") @PathVariable Long id) {
        if (!authorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            authorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    @Operation(
        summary = "🔍 Buscar autores por nombre",
        description = "Busca autores por nombre o parte del nombre"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Author>> searchAuthors(@Parameter(description = "Nombre o parte del nombre del autor", example = "Gabriel") @RequestParam String name) {
        List<Author> authors = authorService.searchAuthors(name);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/nationality/{nationality}")
    @Operation(
        summary = "🌍 Obtener autores por nacionalidad",
        description = "Retorna una lista de autores filtrados por nacionalidad"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "✅ Lista de autores obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "❌ Error interno del servidor")
    })
    public ResponseEntity<List<Author>> getAuthorsByNationality(@Parameter(description = "Nacionalidad de los autores", example = "Colombiana") @PathVariable String nationality) {
        List<Author> authors = authorService.findAuthorsByNationality(nationality);
        return ResponseEntity.ok(authors);
    }
}