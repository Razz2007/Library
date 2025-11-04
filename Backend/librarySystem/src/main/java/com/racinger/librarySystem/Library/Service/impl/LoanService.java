package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Book;
import com.racinger.librarySystem.Library.Entity.Loan;
import com.racinger.librarySystem.Library.Entity.Student;
import com.racinger.librarySystem.Library.Repository.BookRepository;
import com.racinger.librarySystem.Library.Repository.LoanRepository;
import com.racinger.librarySystem.Library.Repository.StudentRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IBookService;
import com.racinger.librarySystem.Library.Service.interfaces.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final IBookService bookService;

    private static final int MAX_LOANS_PER_STUDENT = 3;

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findLoansByStudent(Long studentId) {
        return loanRepository.findByStudentId(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findLoansByBook(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findActiveLoansByStudent(Long studentId) {
        return loanRepository.findActiveLoansByStudent(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findOverdueLoans() {
        return loanRepository.findOverdueLoans(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findLoansByStatus(Loan.LoanStatus status) {
        return loanRepository.findByStatus(status);
    }

    @Override
    public Loan createLoan(Long studentId, Long bookId, LocalDate dueDate) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (studentOpt.isEmpty() || bookOpt.isEmpty()) {
            throw new IllegalArgumentException("Student or Book not found");
        }

        Student student = studentOpt.get();
        Book book = bookOpt.get();

        if (!student.getIsActive()) {
            throw new IllegalArgumentException("Student is not active");
        }

        if (!isBookAvailableForLoan(bookId)) {
            throw new IllegalArgumentException("Book is not available for loan");
        }

        if (!canStudentBorrow(studentId)) {
            throw new IllegalArgumentException("Student has reached maximum loan limit");
        }

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(dueDate);
        loan.setStatus(Loan.LoanStatus.ACTIVE);

        Loan savedLoan = loanRepository.save(loan);
        bookService.updateAvailableCopies(bookId, -1);

        return savedLoan;
    }

    @Override
    public Loan returnBook(Long loanId, LocalDate returnDate) {
        Optional<Loan> loanOpt = loanRepository.findById(loanId);
        if (loanOpt.isEmpty()) {
            throw new IllegalArgumentException("Loan not found");
        }

        Loan loan = loanOpt.get();
        if (loan.getStatus() != Loan.LoanStatus.ACTIVE) {
            throw new IllegalArgumentException("Loan is not active");
        }

        loan.setReturnDate(returnDate);
        loan.setStatus(Loan.LoanStatus.RETURNED);

        bookService.updateAvailableCopies(loan.getBook().getId(), 1);

        return loanRepository.save(loan);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookAvailableForLoan(Long bookId) {
        return bookService.isBookAvailable(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canStudentBorrow(Long studentId) {
        return getActiveLoansCountByStudent(studentId) < MAX_LOANS_PER_STUDENT;
    }

    @Override
    @Transactional(readOnly = true)
    public int getActiveLoansCountByStudent(Long studentId) {
        return loanRepository.findActiveLoansByStudent(studentId).size();
    }
}