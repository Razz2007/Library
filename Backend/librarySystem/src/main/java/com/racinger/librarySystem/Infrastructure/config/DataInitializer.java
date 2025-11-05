package com.racinger.librarySystem.Infrastructure.config;

import com.racinger.librarySystem.Library.Entity.Author;
import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Entity.Category;
import com.racinger.librarySystem.Library.Repository.AuthorRepository;
import com.racinger.librarySystem.Library.Repository.BookRepository;
import com.racinger.librarySystem.Library.Repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CategoryRepository categoryRepository,
                          AuthorRepository authorRepository,
                          BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("🚀 Iniciando inicialización de datos...");

        try {
            initializeCategories();
            initializeAuthors();
            initializeBooks();

            log.info("✅ Inicialización de datos completada exitosamente");
        } catch (Exception e) {
            log.error("❌ Error durante la inicialización de datos", e);
            throw e;
        }
    }

    private void initializeCategories() {
        log.info("📂 Inicializando categorías...");

        if (categoryRepository.count() > 0) {
            log.info("📂 Las categorías ya están inicializadas, omitiendo...");
            return;
        }

        Category[] categories = {
            createCategory("Ficción", "Libros de ficción y narrativa"),
            createCategory("No Ficción", "Libros de no ficción y ensayos"),
            createCategory("Ciencia", "Libros científicos y técnicos"),
            createCategory("Historia", "Libros de historia y biografías"),
            createCategory("Literatura Infantil", "Libros para niños"),
            createCategory("Poesía", "Colecciones de poesía"),
            createCategory("Filosofía", "Obras filosóficas"),
            createCategory("Arte", "Libros de arte y diseño")
        };

        categoryRepository.saveAll(Arrays.asList(categories));
        categoryRepository.flush();
        log.info("📂 {} categorías creadas exitosamente", categories.length);
    }

    private void initializeAuthors() {
        log.info("✍️ Inicializando autores...");

        if (authorRepository.count() > 0) {
            log.info("✍️ Los autores ya están inicializados, omitiendo...");
            return;
        }

        Author[] authors = {
            createAuthor("Gabriel", "García Márquez", "Premio Nobel de Literatura 1982", "Colombiana", 1927),
            createAuthor("Mario", "Vargas Llosa", "Premio Nobel de Literatura 2010", "Peruana", 1936),
            createAuthor("Isabel", "Allende", "Escritora chilena reconocida internacionalmente", "Chilena", 1942),
            createAuthor("Jorge Luis", "Borges", "Maestro del cuento corto y ensayo", "Argentina", 1899),
            createAuthor("Pablo", "Neruda", "Premio Nobel de Literatura 1971", "Chilena", 1904),
            createAuthor("Octavio", "Paz", "Premio Nobel de Literatura 1990", "Mexicana", 1914),
            createAuthor("Julio", "Cortázar", "Innovador en narrativa latinoamericana", "Argentina", 1914),
            createAuthor("Carlos", "Fuentes", "Novelista y ensayista mexicano", "Mexicana", 1928),
            createAuthor("Eduardo", "Galeano", "Historiador y periodista uruguayo", "Uruguaya", 1940),
            createAuthor("José", "Saramago", "Premio Nobel de Literatura 1998", "Portuguesa", 1922)
        };

        authorRepository.saveAll(Arrays.asList(authors));
        authorRepository.flush();
        log.info("✍️ {} autores creados exitosamente", authors.length);
    }

    private void initializeBooks() {
        log.info("📚 Inicializando libros...");

        if (bookRepository.count() > 0) {
            log.info("📚 Los libros ya están inicializados, omitiendo...");
            return;
        }

        // Obtener autores y categorías
        var authors = authorRepository.findAll();
        Author garciaMarquez = authors.stream().filter(a -> a.getFirstName().equals("Gabriel") && a.getLastName().equals("García Márquez")).findFirst().orElseThrow();
        Author vargasLlosa = authors.stream().filter(a -> a.getFirstName().equals("Mario") && a.getLastName().equals("Vargas Llosa")).findFirst().orElseThrow();
        Author allende = authors.stream().filter(a -> a.getFirstName().equals("Isabel") && a.getLastName().equals("Allende")).findFirst().orElseThrow();
        Author borges = authors.stream().filter(a -> a.getFirstName().equals("Jorge Luis") && a.getLastName().equals("Borges")).findFirst().orElseThrow();
        Author neruda = authors.stream().filter(a -> a.getFirstName().equals("Pablo") && a.getLastName().equals("Neruda")).findFirst().orElseThrow();
        Author paz = authors.stream().filter(a -> a.getFirstName().equals("Octavio") && a.getLastName().equals("Paz")).findFirst().orElseThrow();
        Author cortazar = authors.stream().filter(a -> a.getFirstName().equals("Julio") && a.getLastName().equals("Cortázar")).findFirst().orElseThrow();
        Author fuentes = authors.stream().filter(a -> a.getFirstName().equals("Carlos") && a.getLastName().equals("Fuentes")).findFirst().orElseThrow();
        Author galeano = authors.stream().filter(a -> a.getFirstName().equals("Eduardo") && a.getLastName().equals("Galeano")).findFirst().orElseThrow();
        Author saramago = authors.stream().filter(a -> a.getFirstName().equals("José") && a.getLastName().equals("Saramago")).findFirst().orElseThrow();

        var categories = categoryRepository.findAll();
        Category ficcion = categories.stream().filter(c -> c.getName().equals("Ficción")).findFirst().orElseThrow();
        Category poesia = categories.stream().filter(c -> c.getName().equals("Poesía")).findFirst().orElseThrow();
        Category historia = categories.stream().filter(c -> c.getName().equals("Historia")).findFirst().orElseThrow();

        Book[] books = {
            createBook("Cien años de soledad", "La saga de la familia Buendía en Macondo", 1967, "978-84-376-0494-7", 10, 8, ficcion, new Author[]{garciaMarquez}),
            createBook("La ciudad y los perros", "Novela sobre la vida en un colegio militar", 1963, "978-84-206-8187-2", 8, 6, ficcion, new Author[]{vargasLlosa}),
            createBook("La casa de los espíritus", "Historia de la familia Trueba a través de generaciones", 1982, "978-84-204-0121-3", 12, 10, ficcion, new Author[]{allende}),
            createBook("Ficciones", "Colección de cuentos filosóficos", 1944, "978-84-376-0495-4", 6, 4, ficcion, new Author[]{borges}),
            createBook("Veinte poemas de amor y una canción desesperada", "Poesía amorosa del Nobel chileno", 1924, "978-84-206-8188-9", 15, 12, poesia, new Author[]{neruda}),
            createBook("El laberinto de la soledad", "Ensayo sobre la identidad mexicana", 1950, "978-84-206-8189-6", 7, 5, ficcion, new Author[]{paz}),
            createBook("Rayuela", "Novela experimental argentina", 1963, "978-84-376-0496-1", 9, 7, ficcion, new Author[]{cortazar}),
            createBook("La región más transparente", "Novela sobre la Ciudad de México", 1958, "978-84-206-8190-2", 5, 3, ficcion, new Author[]{fuentes}),
            createBook("Las venas abiertas de América Latina", "Análisis histórico del subdesarrollo", 1971, "978-84-323-0112-9", 11, 9, historia, new Author[]{galeano}),
            createBook("Ensayo sobre la ceguera", "Novela distópica sobre una epidemia", 1995, "978-84-204-0122-0", 13, 11, ficcion, new Author[]{saramago}),
            createBook("El amor en los tiempos del cólera", "Historia de amor que dura más de cincuenta años", 1985, "978-84-376-0497-8", 14, 12, ficcion, new Author[]{garciaMarquez}),
            createBook("La fiesta del chivo", "Novela sobre la dictadura de Trujillo", 2000, "978-84-204-0123-7", 8, 6, ficcion, new Author[]{vargasLlosa}),
            createBook("De amor y de sombra", "Historia de amor en tiempos de dictadura", 1984, "978-84-204-0124-4", 10, 8, ficcion, new Author[]{allende}),
            createBook("El Aleph", "Colección de cuentos fantásticos", 1949, "978-84-376-0498-5", 7, 5, ficcion, new Author[]{borges}),
            createBook("Confieso que he vivido", "Memorias del poeta chileno", 1974, "978-84-206-8191-9", 6, 4, ficcion, new Author[]{neruda})
        };

        bookRepository.saveAll(Arrays.asList(books));
        log.info("📚 {} libros creados exitosamente", books.length);
    }

    private Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return category;
    }

    private Author createAuthor(String firstName, String lastName, String biography, String nationality, int birthYear) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBiography(biography);
        author.setNationality(nationality);
        author.setBirthYear(birthYear);
        return author;
    }

    private Book createBook(String title, String description, int publicationYear, String isbn,
                           int totalCopies, int availableCopies, Category category, Author[] authors) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        book.setTotalCopies(totalCopies);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);
        book.setAuthors(new HashSet<>(Arrays.asList(authors)));
        return book;
    }
}