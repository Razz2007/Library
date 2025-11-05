-- Insertar datos de ejemplo para el sistema de biblioteca - SQL Server Version
-- Version: 2.0.0

-- Insertar categorías (sin dependencias)
IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Ficción')
INSERT INTO categories (name, description) VALUES
('Ficción', 'Libros de ficción y literatura narrativa');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'No Ficción')
INSERT INTO categories (name, description) VALUES
('No Ficción', 'Libros basados en hechos reales');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Ciencia')
INSERT INTO categories (name, description) VALUES
('Ciencia', 'Libros científicos y técnicos');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Historia')
INSERT INTO categories (name, description) VALUES
('Historia', 'Libros de historia y biografías');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Tecnología')
INSERT INTO categories (name, description) VALUES
('Tecnología', 'Libros sobre tecnología e informática');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Arte')
INSERT INTO categories (name, description) VALUES
('Arte', 'Libros de arte y diseño');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Filosofía')
INSERT INTO categories (name, description) VALUES
('Filosofía', 'Libros de filosofía y pensamiento');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Medicina')
INSERT INTO categories (name, description) VALUES
('Medicina', 'Libros médicos y de salud');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Derecho')
INSERT INTO categories (name, description) VALUES
('Derecho', 'Libros de derecho y jurisprudencia');

IF NOT EXISTS (SELECT 1 FROM categories WHERE name = 'Economía')
INSERT INTO categories (name, description) VALUES
('Economía', 'Libros de economía y finanzas');

-- Insertar autores (sin dependencias)
IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Gabriel' AND last_name = 'García Márquez')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Gabriel', 'García Márquez', 'Premio Nobel de Literatura', 'Colombiano', 1927);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Mario' AND last_name = 'Vargas Llosa')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Mario', 'Vargas Llosa', 'Escritor peruano, Premio Nobel', 'Peruano', 1936);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Isabel' AND last_name = 'Allende')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Isabel', 'Allende', 'Escritora chilena', 'Chilena', 1942);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Jorge Luis' AND last_name = 'Borges')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Jorge Luis', 'Borges', 'Escritor argentino', 'Argentino', 1899);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Julio' AND last_name = 'Cortázar')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Julio', 'Cortázar', 'Escritor argentino', 'Argentino', 1914);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Pablo' AND last_name = 'Neruda')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Pablo', 'Neruda', 'Poeta chileno, Premio Nobel', 'Chileno', 1904);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Octavio' AND last_name = 'Paz')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Octavio', 'Paz', 'Poeta mexicano, Premio Nobel', 'Mexicano', 1914);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Carlos' AND last_name = 'Fuentes')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Carlos', 'Fuentes', 'Escritor mexicano', 'Mexicano', 1928);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Eduardo' AND last_name = 'Galeano')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Eduardo', 'Galeano', 'Escritor uruguayo', 'Uruguayo', 1940);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'José' AND last_name = 'Saramago')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('José', 'Saramago', 'Escritor portugués, Premio Nobel', 'Portugués', 1922);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Albert' AND last_name = 'Einstein')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Albert', 'Einstein', 'Físico alemán', 'Alemán', 1879);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Stephen' AND last_name = 'Hawking')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Stephen', 'Hawking', 'Físico británico', 'Británico', 1942);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Richard' AND last_name = 'Feynman')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Richard', 'Feynman', 'Físico estadounidense', 'Estadounidense', 1918);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Carl' AND last_name = 'Sagan')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Carl', 'Sagan', 'Astrónomo estadounidense', 'Estadounidense', 1934);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Neil' AND last_name = 'deGrasse Tyson')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Neil', 'deGrasse Tyson', 'Astrónomo estadounidense', 'Estadounidense', 1958);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Yuval Noah' AND last_name = 'Harari')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Yuval Noah', 'Harari', 'Historiador israelí', 'Israelí', 1976);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Howard' AND last_name = 'Zinn')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Howard', 'Zinn', 'Historiador estadounidense', 'Estadounidense', 1922);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Eric' AND last_name = 'Hobsbawm')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Eric', 'Hobsbawm', 'Historiador británico', 'Británico', 1917);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Simon' AND last_name = 'Schama')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Simon', 'Schama', 'Historiador británico', 'Británico', 1945);

IF NOT EXISTS (SELECT 1 FROM authors WHERE first_name = 'Jared' AND last_name = 'Diamond')
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Jared', 'Diamond', 'Geógrafo estadounidense', 'Estadounidense', 1937);

-- Insertar estudiantes (sin dependencias)
IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'juan.perez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Juan', 'Pérez', 'juan.perez@email.com', '+57 300 123 4567', '2023-01-15', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'maria.garcia@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('María', 'García', 'maria.garcia@email.com', '+57 301 234 5678', '2023-02-20', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'carlos.rodriguez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Carlos', 'Rodríguez', 'carlos.rodriguez@email.com', '+57 302 345 6789', '2023-03-10', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'ana.martinez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Ana', 'Martínez', 'ana.martinez@email.com', '+57 303 456 7890', '2023-04-05', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'luis.hernandez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Luis', 'Hernández', 'luis.hernandez@email.com', '+57 304 567 8901', '2023-05-12', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'sofia.lopez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Sofia', 'López', 'sofia.lopez@email.com', '+57 305 678 9012', '2023-06-18', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'diego.gonzalez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Diego', 'González', 'diego.gonzalez@email.com', '+57 306 789 0123', '2023-07-22', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'valentina.diaz@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Valentina', 'Díaz', 'valentina.diaz@email.com', '+57 307 890 1234', '2023-08-30', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'andres.torres@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Andrés', 'Torres', 'andres.torres@email.com', '+57 308 901 2345', '2023-09-14', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'camila.ramirez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Camila', 'Ramírez', 'camila.ramirez@email.com', '+57 309 012 3456', '2023-10-08', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'mateo.flores@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Mateo', 'Flores', 'mateo.flores@email.com', '+57 310 123 4567', '2023-11-25', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'isabella.morales@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Isabella', 'Morales', 'isabella.morales@email.com', '+57 311 234 5678', '2023-12-03', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'santiago.ortiz@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Santiago', 'Ortiz', 'santiago.ortiz@email.com', '+57 312 345 6789', '2024-01-17', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'gabriela.gutierrez@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Gabriela', 'Gutiérrez', 'gabriela.gutierrez@email.com', '+57 313 456 7890', '2024-02-28', 1);

IF NOT EXISTS (SELECT 1 FROM students WHERE email = 'daniel.castillo@email.com')
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Daniel', 'Castillo', 'daniel.castillo@email.com', '+57 314 567 8901', '2024-03-15', 1);

-- Insertar libros (depende de categorías)
IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0494-7')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Cien años de soledad', 'Novela magistral de Gabriel García Márquez', 1967, '978-84-376-0494-7', 5, 5, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-204-0121-4')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('La ciudad y los perros', 'Primera novela de Mario Vargas Llosa', 1963, '978-84-204-0121-4', 3, 3, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-204-0122-1')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('La casa de los espíritus', 'Novela de Isabel Allende', 1982, '978-84-204-0122-1', 4, 4, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0495-4')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Ficciones', 'Colección de cuentos de Jorge Luis Borges', 1944, '978-84-376-0495-4', 2, 2, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0496-1')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Rayuela', 'Novela de Julio Cortázar', 1963, '978-84-376-0496-1', 3, 3, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0497-8')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Veinte poemas de amor', 'Poesía de Pablo Neruda', 1924, '978-84-376-0497-8', 4, 4, 6);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0498-5')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('El laberinto de la soledad', 'Ensayo de Octavio Paz', 1950, '978-84-376-0498-5', 2, 2, 7);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0499-2')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('La muerte de Artemio Cruz', 'Novela de Carlos Fuentes', 1962, '978-84-376-0499-2', 3, 3, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0500-5')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Las venas abiertas de América Latina', 'Ensayo de Eduardo Galeano', 1971, '978-84-376-0500-5', 3, 3, 4);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0501-2')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Ensayo sobre la ceguera', 'Novela de José Saramago', 1995, '978-84-376-0501-2', 4, 4, 1);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0502-9')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Relatividad', 'Explicación de la teoría de la relatividad', 1916, '978-84-376-0502-9', 2, 2, 3);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0503-6')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Breve historia del tiempo', 'Explicación del universo', 1988, '978-84-376-0503-6', 5, 5, 3);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0504-3')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('¿Está usted de broma, Mr. Feynman?', 'Memorias de Richard Feynman', 1985, '978-84-376-0504-3', 3, 3, 3);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0505-0')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Cosmos', 'Viaje a través del universo', 1980, '978-84-376-0505-0', 4, 4, 3);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0506-7')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Astrophysics for People in a Hurry', 'Introducción a la astrofísica', 2017, '978-84-376-0506-7', 3, 3, 3);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0507-4')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Sapiens: De animales a dioses', 'Historia de la humanidad', 2011, '978-84-376-0507-4', 6, 6, 4);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0508-1')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Una historia del pueblo norteamericano', 'Historia de Estados Unidos', 1980, '978-84-376-0508-1', 2, 2, 4);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0509-8')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('La era de los extremos', 'Historia del siglo XX', 1994, '978-84-376-0509-8', 3, 3, 4);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0510-4')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Historia de Inglaterra', 'Historia británica', 2000, '978-84-376-0510-4', 2, 2, 4);

IF NOT EXISTS (SELECT 1 FROM books WHERE isbn = '978-84-376-0511-1')
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Armas, gérmenes y acero', 'Por qué las sociedades europeas conquistaron', 1997, '978-84-376-0511-1', 4, 4, 4);

-- Insertar relaciones libro-autor (depende de libros y autores)
IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 1 AND author_id = 1)
INSERT INTO book_authors (book_id, author_id) VALUES (1, 1);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 2 AND author_id = 2)
INSERT INTO book_authors (book_id, author_id) VALUES (2, 2);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 3 AND author_id = 3)
INSERT INTO book_authors (book_id, author_id) VALUES (3, 3);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 4 AND author_id = 4)
INSERT INTO book_authors (book_id, author_id) VALUES (4, 4);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 5 AND author_id = 5)
INSERT INTO book_authors (book_id, author_id) VALUES (5, 5);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 6 AND author_id = 6)
INSERT INTO book_authors (book_id, author_id) VALUES (6, 6);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 7 AND author_id = 7)
INSERT INTO book_authors (book_id, author_id) VALUES (7, 7);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 8 AND author_id = 8)
INSERT INTO book_authors (book_id, author_id) VALUES (8, 8);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 9 AND author_id = 9)
INSERT INTO book_authors (book_id, author_id) VALUES (9, 9);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 10 AND author_id = 10)
INSERT INTO book_authors (book_id, author_id) VALUES (10, 10);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 11 AND author_id = 11)
INSERT INTO book_authors (book_id, author_id) VALUES (11, 11);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 12 AND author_id = 12)
INSERT INTO book_authors (book_id, author_id) VALUES (12, 12);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 13 AND author_id = 13)
INSERT INTO book_authors (book_id, author_id) VALUES (13, 13);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 14 AND author_id = 14)
INSERT INTO book_authors (book_id, author_id) VALUES (14, 14);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 15 AND author_id = 15)
INSERT INTO book_authors (book_id, author_id) VALUES (15, 15);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 16 AND author_id = 16)
INSERT INTO book_authors (book_id, author_id) VALUES (16, 16);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 17 AND author_id = 17)
INSERT INTO book_authors (book_id, author_id) VALUES (17, 17);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 18 AND author_id = 18)
INSERT INTO book_authors (book_id, author_id) VALUES (18, 18);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 19 AND author_id = 19)
INSERT INTO book_authors (book_id, author_id) VALUES (19, 19);

IF NOT EXISTS (SELECT 1 FROM book_authors WHERE book_id = 20 AND author_id = 20)
INSERT INTO book_authors (book_id, author_id) VALUES (20, 20);

-- Insertar préstamos (depende de estudiantes y libros)
IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0494-7') AND loan_date = '2024-10-01')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'juan.perez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0494-7'), '2024-10-01', '2024-10-15', 'RETURNED', '2024-10-12');

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'maria.garcia@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-204-0122-1') AND loan_date = '2024-10-05')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'maria.garcia@email.com'), (SELECT id FROM books WHERE isbn = '978-84-204-0122-1'), '2024-10-05', '2024-10-19', 'ACTIVE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0496-1') AND loan_date = '2024-10-08')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0496-1'), '2024-10-08', '2024-10-22', 'ACTIVE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'ana.martinez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0498-5') AND loan_date = '2024-10-10')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'ana.martinez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0498-5'), '2024-10-10', '2024-10-24', 'OVERDUE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'luis.hernandez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0500-5') AND loan_date = '2024-10-12')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'luis.hernandez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0500-5'), '2024-10-12', '2024-10-26', 'ACTIVE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'sofia.lopez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0502-9') AND loan_date = '2024-10-15')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'sofia.lopez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0502-9'), '2024-10-15', '2024-10-29', 'RETURNED', '2024-10-28');

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'diego.gonzalez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0504-3') AND loan_date = '2024-10-18')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'diego.gonzalez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0504-3'), '2024-10-18', '2024-11-01', 'ACTIVE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'valentina.diaz@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0506-7') AND loan_date = '2024-10-20')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'valentina.diaz@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0506-7'), '2024-10-20', '2024-11-03', 'ACTIVE', NULL);

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'andres.torres@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0508-1') AND loan_date = '2024-10-22')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'andres.torres@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0508-1'), '2024-10-22', '2024-11-05', 'RETURNED', '2024-11-02');

IF NOT EXISTS (SELECT 1 FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'camila.ramirez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0510-4') AND loan_date = '2024-10-25')
INSERT INTO loans (student_id, book_id, loan_date, due_date, status, return_date) VALUES
((SELECT id FROM students WHERE email = 'camila.ramirez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0510-4'), '2024-10-25', '2024-11-08', 'ACTIVE', NULL);

-- Insertar devoluciones (depende de préstamos)
IF NOT EXISTS (SELECT 1 FROM returns WHERE loan_id = (SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0494-7') AND loan_date = '2024-10-01'))
INSERT INTO returns (loan_id, return_date, days_late, penalty_amount, notes) VALUES
((SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0494-7') AND loan_date = '2024-10-01'), '2024-10-12', 0, 0.00, 'Devuelto en buen estado');

IF NOT EXISTS (SELECT 1 FROM returns WHERE loan_id = (SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'sofia.lopez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0502-9') AND loan_date = '2024-10-15'))
INSERT INTO returns (loan_id, return_date, days_late, penalty_amount, notes) VALUES
((SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'sofia.lopez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0502-9') AND loan_date = '2024-10-15'), '2024-10-28', 0, 0.00, 'Devuelto a tiempo');

IF NOT EXISTS (SELECT 1 FROM returns WHERE loan_id = (SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'andres.torres@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0508-1') AND loan_date = '2024-10-22'))
INSERT INTO returns (loan_id, return_date, days_late, penalty_amount, notes) VALUES
((SELECT id FROM loans WHERE student_id = (SELECT id FROM students WHERE email = 'andres.torres@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0508-1') AND loan_date = '2024-10-22'), '2024-11-02', 0, 0.00, 'Devuelto sin daños');

-- Insertar penalizaciones (depende de estudiantes)
IF NOT EXISTS (SELECT 1 FROM penalties WHERE student_id = (SELECT id FROM students WHERE email = 'ana.martinez@email.com') AND issued_date = '2024-10-25')
INSERT INTO penalties (student_id, amount, reason, issued_date, status) VALUES
((SELECT id FROM students WHERE email = 'ana.martinez@email.com'), 5000.00, 'Devolución tardía', '2024-10-25', 'PENDING');

IF NOT EXISTS (SELECT 1 FROM penalties WHERE student_id = (SELECT id FROM students WHERE email = 'isabella.morales@email.com') AND issued_date = '2024-09-15')
INSERT INTO penalties (student_id, amount, reason, issued_date, status) VALUES
((SELECT id FROM students WHERE email = 'isabella.morales@email.com'), 10000.00, 'Libro dañado', '2024-09-15', 'PAID');

IF NOT EXISTS (SELECT 1 FROM penalties WHERE student_id = (SELECT id FROM students WHERE email = 'santiago.ortiz@email.com') AND issued_date = '2024-08-20')
INSERT INTO penalties (student_id, amount, reason, issued_date, status) VALUES
((SELECT id FROM students WHERE email = 'santiago.ortiz@email.com'), 3000.00, 'Pérdida de libro', '2024-08-20', 'CANCELLED');

-- Insertar reservaciones (depende de estudiantes y libros)
IF NOT EXISTS (SELECT 1 FROM reservations WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-204-0121-4') AND reservation_date = '2024-10-28')
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
((SELECT id FROM students WHERE email = 'juan.perez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-204-0121-4'), '2024-10-28', '2024-11-11', 'ACTIVE');

IF NOT EXISTS (SELECT 1 FROM reservations WHERE student_id = (SELECT id FROM students WHERE email = 'maria.garcia@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0495-4') AND reservation_date = '2024-10-30')
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
((SELECT id FROM students WHERE email = 'maria.garcia@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0495-4'), '2024-10-30', '2024-11-13', 'FULFILLED');

IF NOT EXISTS (SELECT 1 FROM reservations WHERE student_id = (SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0497-8') AND reservation_date = '2024-11-01')
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
((SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0497-8'), '2024-11-01', '2024-11-15', 'ACTIVE');

IF NOT EXISTS (SELECT 1 FROM reservations WHERE student_id = (SELECT id FROM students WHERE email = 'ana.martinez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0499-2') AND reservation_date = '2024-11-03')
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
((SELECT id FROM students WHERE email = 'ana.martinez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0499-2'), '2024-11-03', '2024-11-17', 'CANCELLED');

IF NOT EXISTS (SELECT 1 FROM reservations WHERE student_id = (SELECT id FROM students WHERE email = 'luis.hernandez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0501-2') AND reservation_date = '2024-11-05')
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
((SELECT id FROM students WHERE email = 'luis.hernandez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0501-2'), '2024-11-05', '2024-11-19', 'EXPIRED');

-- Insertar historial de préstamos por estudiante (depende de estudiantes y libros)
IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0494-7'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'juan.perez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0494-7'), 2);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'juan.perez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-204-0121-4'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'juan.perez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-204-0121-4'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'maria.garcia@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-204-0122-1'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'maria.garcia@email.com'), (SELECT id FROM books WHERE isbn = '978-84-204-0122-1'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'maria.garcia@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0495-4'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'maria.garcia@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0495-4'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0496-1'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0496-1'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0497-8'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'carlos.rodriguez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0497-8'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'ana.martinez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0498-5'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'ana.martinez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0498-5'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'ana.martinez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0499-2'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'ana.martinez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0499-2'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'luis.hernandez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0500-5'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'luis.hernandez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0500-5'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'luis.hernandez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0501-2'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'luis.hernandez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0501-2'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'sofia.lopez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0502-9'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'sofia.lopez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0502-9'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'sofia.lopez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0503-6'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'sofia.lopez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0503-6'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'diego.gonzalez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0504-3'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'diego.gonzalez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0504-3'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'diego.gonzalez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0505-0'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'diego.gonzalez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0505-0'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'valentina.diaz@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0506-7'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'valentina.diaz@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0506-7'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'valentina.diaz@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0507-4'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'valentina.diaz@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0507-4'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'andres.torres@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0508-1'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'andres.torres@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0508-1'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'andres.torres@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0509-8'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'andres.torres@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0509-8'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'camila.ramirez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0510-4'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'camila.ramirez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0510-4'), 1);

IF NOT EXISTS (SELECT 1 FROM student_books WHERE student_id = (SELECT id FROM students WHERE email = 'camila.ramirez@email.com') AND book_id = (SELECT id FROM books WHERE isbn = '978-84-376-0511-1'))
INSERT INTO student_books (student_id, book_id, loan_count) VALUES ((SELECT id FROM students WHERE email = 'camila.ramirez@email.com'), (SELECT id FROM books WHERE isbn = '978-84-376-0511-1'), 1);