-- Insertar datos de ejemplo para el sistema de biblioteca - PostgreSQL Version
-- Version: 2.0.0

-- Insertar categorías
INSERT INTO categories (name, description) VALUES
('Ficción', 'Libros de ficción y literatura narrativa'),
('No Ficción', 'Libros basados en hechos reales'),
('Ciencia', 'Libros científicos y técnicos'),
('Historia', 'Libros de historia y biografías'),
('Tecnología', 'Libros sobre tecnología e informática'),
('Arte', 'Libros de arte y diseño'),
('Filosofía', 'Libros de filosofía y pensamiento'),
('Medicina', 'Libros médicos y de salud'),
('Derecho', 'Libros de derecho y jurisprudencia'),
('Economía', 'Libros de economía y finanzas');

-- Insertar autores
INSERT INTO authors (first_name, last_name, biography, nationality, birth_year) VALUES
('Gabriel', 'García Márquez', 'Premio Nobel de Literatura', 'Colombiano', 1927),
('Mario', 'Vargas Llosa', 'Escritor peruano, Premio Nobel', 'Peruano', 1936),
('Isabel', 'Allende', 'Escritora chilena', 'Chilena', 1942),
('Jorge Luis', 'Borges', 'Escritor argentino', 'Argentino', 1899),
('Julio', 'Cortázar', 'Escritor argentino', 'Argentino', 1914),
('Pablo', 'Neruda', 'Poeta chileno, Premio Nobel', 'Chileno', 1904),
('Octavio', 'Paz', 'Poeta mexicano, Premio Nobel', 'Mexicano', 1914),
('Carlos', 'Fuentes', 'Escritor mexicano', 'Mexicano', 1928),
('Eduardo', 'Galeano', 'Escritor uruguayo', 'Uruguayo', 1940),
('José', 'Saramago', 'Escritor portugués, Premio Nobel', 'Portugués', 1922),
('Albert', 'Einstein', 'Físico alemán', 'Alemán', 1879),
('Stephen', 'Hawking', 'Físico británico', 'Británico', 1942),
('Richard', 'Feynman', 'Físico estadounidense', 'Estadounidense', 1918),
('Carl', 'Sagan', 'Astrónomo estadounidense', 'Estadounidense', 1934),
('Neil', 'deGrasse Tyson', 'Astrónomo estadounidense', 'Estadounidense', 1958),
('Yuval Noah', 'Harari', 'Historiador israelí', 'Israelí', 1976),
('Howard', 'Zinn', 'Historiador estadounidense', 'Estadounidense', 1922),
('Eric', 'Hobsbawm', 'Historiador británico', 'Británico', 1917),
('Simon', 'Schama', 'Historiador británico', 'Británico', 1945),
('Jared', 'Diamond', 'Geógrafo estadounidense', 'Estadounidense', 1937);

-- Insertar libros
INSERT INTO books (title, description, publication_year, isbn, total_copies, available_copies, category_id) VALUES
('Cien años de soledad', 'Novela magistral de Gabriel García Márquez', 1967, '978-84-376-0494-7', 5, 5, 1),
('La ciudad y los perros', 'Primera novela de Mario Vargas Llosa', 1963, '978-84-204-0121-4', 3, 3, 1),
('La casa de los espíritus', 'Novela de Isabel Allende', 1982, '978-84-204-0122-1', 4, 4, 1),
('Ficciones', 'Colección de cuentos de Jorge Luis Borges', 1944, '978-84-376-0495-4', 2, 2, 1),
('Rayuela', 'Novela de Julio Cortázar', 1963, '978-84-376-0496-1', 3, 3, 1),
('Veinte poemas de amor', 'Poesía de Pablo Neruda', 1924, '978-84-376-0497-8', 4, 4, 6),
('El laberinto de la soledad', 'Ensayo de Octavio Paz', 1950, '978-84-376-0498-5', 2, 2, 7),
('La muerte de Artemio Cruz', 'Novela de Carlos Fuentes', 1962, '978-84-376-0499-2', 3, 3, 1),
('Las venas abiertas de América Latina', 'Ensayo de Eduardo Galeano', 1971, '978-84-376-0500-5', 3, 3, 4),
('Ensayo sobre la ceguera', 'Novela de José Saramago', 1995, '978-84-376-0501-2', 4, 4, 1),
('Relatividad', 'Explicación de la teoría de la relatividad', 1916, '978-84-376-0502-9', 2, 2, 3),
('Breve historia del tiempo', 'Explicación del universo', 1988, '978-84-376-0503-6', 5, 5, 3),
('¿Está usted de broma, Mr. Feynman?', 'Memorias de Richard Feynman', 1985, '978-84-376-0504-3', 3, 3, 3),
('Cosmos', 'Viaje a través del universo', 1980, '978-84-376-0505-0', 4, 4, 3),
('Astrophysics for People in a Hurry', 'Introducción a la astrofísica', 2017, '978-84-376-0506-7', 3, 3, 3),
('Sapiens: De animales a dioses', 'Historia de la humanidad', 2011, '978-84-376-0507-4', 6, 6, 4),
('Una historia del pueblo norteamericano', 'Historia de Estados Unidos', 1980, '978-84-376-0508-1', 2, 2, 4),
('La era de los extremos', 'Historia del siglo XX', 1994, '978-84-376-0509-8', 3, 3, 4),
('Historia de Inglaterra', 'Historia británica', 2000, '978-84-376-0510-4', 2, 2, 4),
('Armas, gérmenes y acero', 'Por qué las sociedades europeas conquistaron', 1997, '978-84-376-0511-1', 4, 4, 4);

-- Insertar relaciones libro-autor
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16), (17, 17), (18, 18), (19, 19), (20, 20);

-- Insertar estudiantes
INSERT INTO students (first_name, last_name, email, phone, registration_date, is_active) VALUES
('Juan', 'Pérez', 'juan.perez@email.com', '+57 300 123 4567', '2023-01-15', true),
('María', 'García', 'maria.garcia@email.com', '+57 301 234 5678', '2023-02-20', true),
('Carlos', 'Rodríguez', 'carlos.rodriguez@email.com', '+57 302 345 6789', '2023-03-10', true),
('Ana', 'Martínez', 'ana.martinez@email.com', '+57 303 456 7890', '2023-04-05', true),
('Luis', 'Hernández', 'luis.hernandez@email.com', '+57 304 567 8901', '2023-05-12', true),
('Sofia', 'López', 'sofia.lopez@email.com', '+57 305 678 9012', '2023-06-18', true),
('Diego', 'González', 'diego.gonzalez@email.com', '+57 306 789 0123', '2023-07-22', true),
('Valentina', 'Díaz', 'valentina.diaz@email.com', '+57 307 890 1234', '2023-08-30', true),
('Andrés', 'Torres', 'andres.torres@email.com', '+57 308 901 2345', '2023-09-14', true),
('Camila', 'Ramírez', 'camila.ramirez@email.com', '+57 309 012 3456', '2023-10-08', true),
('Mateo', 'Flores', 'mateo.flores@email.com', '+57 310 123 4567', '2023-11-25', true),
('Isabella', 'Morales', 'isabella.morales@email.com', '+57 311 234 5678', '2023-12-03', true),
('Santiago', 'Ortiz', 'santiago.ortiz@email.com', '+57 312 345 6789', '2024-01-17', true),
('Gabriela', 'Gutiérrez', 'gabriela.gutierrez@email.com', '+57 313 456 7890', '2024-02-28', true),
('Daniel', 'Castillo', 'daniel.castillo@email.com', '+57 314 567 8901', '2024-03-15', true);

-- Insertar préstamos
INSERT INTO loans (student_id, book_id, loan_date, due_date, status) VALUES
(1, 1, '2024-10-01', '2024-10-15', 'RETURNED'),
(2, 3, '2024-10-05', '2024-10-19', 'ACTIVE'),
(3, 5, '2024-10-08', '2024-10-22', 'ACTIVE'),
(4, 7, '2024-10-10', '2024-10-24', 'OVERDUE'),
(5, 9, '2024-10-12', '2024-10-26', 'ACTIVE'),
(6, 11, '2024-10-15', '2024-10-29', 'RETURNED'),
(7, 13, '2024-10-18', '2024-11-01', 'ACTIVE'),
(8, 15, '2024-10-20', '2024-11-03', 'ACTIVE'),
(9, 17, '2024-10-22', '2024-11-05', 'RETURNED'),
(10, 19, '2024-10-25', '2024-11-08', 'ACTIVE');

-- Insertar devoluciones
INSERT INTO returns (loan_id, return_date, days_late, penalty_amount, notes) VALUES
(1, '2024-10-12', 0, 0.00, 'Devuelto en buen estado'),
(6, '2024-10-28', 0, 0.00, 'Devuelto a tiempo'),
(9, '2024-11-02', 0, 0.00, 'Devuelto sin daños');

-- Insertar penalizaciones
INSERT INTO penalties (student_id, amount, reason, issued_date, status) VALUES
(4, 5000.00, 'Devolución tardía', '2024-10-25', 'PENDING'),
(11, 10000.00, 'Libro dañado', '2024-09-15', 'PAID'),
(12, 3000.00, 'Pérdida de libro', '2024-08-20', 'CANCELLED');

-- Insertar reservaciones
INSERT INTO reservations (student_id, book_id, reservation_date, expiration_date, status) VALUES
(1, 2, '2024-10-28', '2024-11-11', 'ACTIVE'),
(2, 4, '2024-10-30', '2024-11-13', 'FULFILLED'),
(3, 6, '2024-11-01', '2024-11-15', 'ACTIVE'),
(4, 8, '2024-11-03', '2024-11-17', 'CANCELLED'),
(5, 10, '2024-11-05', '2024-11-19', 'EXPIRED');

-- Insertar historial de préstamos por estudiante
INSERT INTO student_books (student_id, book_id, loan_count) VALUES
(1, 1, 2), (1, 2, 1), (2, 3, 1), (2, 4, 1), (3, 5, 1),
(3, 6, 1), (4, 7, 1), (4, 8, 1), (5, 9, 1), (5, 10, 1),
(6, 11, 1), (6, 12, 1), (7, 13, 1), (7, 14, 1), (8, 15, 1),
(8, 16, 1), (9, 17, 1), (9, 18, 1), (10, 19, 1), (10, 20, 1);