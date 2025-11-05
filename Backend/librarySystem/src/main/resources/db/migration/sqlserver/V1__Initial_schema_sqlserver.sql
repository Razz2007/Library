-- Initial schema for Library System - SQL Server Version
-- Version: 1.0.0

-- Categories table
CREATE TABLE categories (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE,
    description NVARCHAR(MAX)
);

-- Authors table
CREATE TABLE authors (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
    biography NVARCHAR(MAX),
    nationality NVARCHAR(100),
    birth_year INT
);

-- Books table
CREATE TABLE books (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX),
    publication_year INT NOT NULL,
    isbn NVARCHAR(50) NOT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Book-Author relationship table
CREATE TABLE book_authors (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
    CONSTRAINT unique_book_author UNIQUE (book_id, author_id)
);

-- Students table
CREATE TABLE students (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    first_name NVARCHAR(100) NOT NULL,
    last_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    phone NVARCHAR(20),
    registration_date DATE NOT NULL,
    is_active BIT NOT NULL DEFAULT 1
);

-- Loans table
CREATE TABLE loans (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    student_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE')),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Returns table
CREATE TABLE returns (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    loan_id BIGINT NOT NULL UNIQUE,
    return_date DATE NOT NULL,
    days_late INT,
    penalty_amount DECIMAL(10,2),
    notes NVARCHAR(MAX),
    FOREIGN KEY (loan_id) REFERENCES loans(id) ON DELETE CASCADE
);

-- Penalties table
CREATE TABLE penalties (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    student_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    reason NVARCHAR(255) NOT NULL,
    issued_date DATE NOT NULL,
    paid_date DATE,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'PAID', 'CANCELLED')),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

-- Reservations table
CREATE TABLE reservations (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    student_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    reservation_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'CANCELLED', 'EXPIRED', 'FULFILLED')),
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Student-Book relationship table (for tracking loan history)
CREATE TABLE student_books (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    student_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    loan_count INT NOT NULL DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    CONSTRAINT unique_student_book UNIQUE (student_id, book_id)
);

-- Indexes for better performance
CREATE INDEX idx_books_category ON books(category_id);
CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_loans_student ON loans(student_id);
CREATE INDEX idx_loans_book ON loans(book_id);
CREATE INDEX idx_loans_status ON loans(status);
CREATE INDEX idx_loans_due_date ON loans(due_date);
CREATE INDEX idx_returns_return_date ON returns(return_date);
CREATE INDEX idx_penalties_student ON penalties(student_id);
CREATE INDEX idx_penalties_status ON penalties(status);
CREATE INDEX idx_reservations_student ON reservations(student_id);
CREATE INDEX idx_reservations_book ON reservations(book_id);
CREATE INDEX idx_reservations_status ON reservations(status);
CREATE INDEX idx_students_email ON students(email);
CREATE INDEX idx_students_active ON students(is_active);