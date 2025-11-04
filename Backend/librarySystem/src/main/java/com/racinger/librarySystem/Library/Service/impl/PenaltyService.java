package com.racinger.librarySystem.Library.Service.impl;

import com.racinger.librarySystem.Library.Entity.Penalty;
import com.racinger.librarySystem.Library.Entity.Student;
import com.racinger.librarySystem.Library.Repository.PenaltyRepository;
import com.racinger.librarySystem.Library.Repository.StudentRepository;
import com.racinger.librarySystem.Library.Service.interfaces.IPenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PenaltyService implements IPenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findAll() {
        return penaltyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Penalty> findById(Long id) {
        return penaltyRepository.findById(id);
    }

    @Override
    public Penalty save(Penalty penalty) {
        return penaltyRepository.save(penalty);
    }

    @Override
    public void deleteById(Long id) {
        penaltyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findPenaltiesByStudent(Long studentId) {
        return penaltyRepository.findByStudentId(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findPenaltiesByStatus(Penalty.PenaltyStatus status) {
        return penaltyRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Penalty> findPenaltiesInDateRange(LocalDate startDate, LocalDate endDate) {
        return penaltyRepository.findPenaltiesInDateRange(startDate, endDate);
    }

    @Override
    public Penalty createPenalty(Long studentId, Double amount, String reason) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }

        Penalty penalty = new Penalty();
        penalty.setStudent(studentOpt.get());
        penalty.setAmount(amount);
        penalty.setReason(reason);
        penalty.setIssuedDate(LocalDate.now());
        penalty.setStatus(Penalty.PenaltyStatus.PENDING);

        return penaltyRepository.save(penalty);
    }

    @Override
    public void payPenalty(Long penaltyId) {
        Optional<Penalty> penaltyOpt = penaltyRepository.findById(penaltyId);
        if (penaltyOpt.isEmpty()) {
            throw new IllegalArgumentException("Penalty not found");
        }

        Penalty penalty = penaltyOpt.get();
        if (penalty.getStatus() != Penalty.PenaltyStatus.PENDING) {
            throw new IllegalArgumentException("Penalty is not pending");
        }

        penalty.setStatus(Penalty.PenaltyStatus.PAID);
        penalty.setPaidDate(LocalDate.now());
        penaltyRepository.save(penalty);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getTotalPendingPenaltiesByStudent(Long studentId) {
        return penaltyRepository.sumPendingPenalties();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasPendingPenalties(Long studentId) {
        return penaltyRepository.countPendingPenaltiesByStudent(studentId) > 0;
    }
}