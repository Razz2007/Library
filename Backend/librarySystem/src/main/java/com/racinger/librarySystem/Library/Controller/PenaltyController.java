package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Penalty;
import com.racinger.librarySystem.Library.Service.interfaces.IPenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
public class PenaltyController {

    private final IPenaltyService penaltyService;

    @GetMapping
    public ResponseEntity<List<Penalty>> getAllPenalties() {
        List<Penalty> penalties = penaltyService.findAll();
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penalty> getPenaltyById(@PathVariable Long id) {
        Optional<Penalty> penalty = penaltyService.findById(id);
        return penalty.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Penalty> createPenalty(@RequestParam Long studentId,
                                                 @RequestParam Double amount,
                                                 @RequestParam String reason) {
        try {
            Penalty penalty = penaltyService.createPenalty(studentId, amount, reason);
            return ResponseEntity.ok(penalty);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<Void> payPenalty(@PathVariable Long id) {
        try {
            penaltyService.payPenalty(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Penalty>> getPenaltiesByStudent(@PathVariable Long studentId) {
        List<Penalty> penalties = penaltyService.findPenaltiesByStudent(studentId);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Penalty>> getPenaltiesByStatus(@PathVariable Penalty.PenaltyStatus status) {
        List<Penalty> penalties = penaltyService.findPenaltiesByStatus(status);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Penalty>> getPenaltiesInDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Penalty> penalties = penaltyService.findPenaltiesInDateRange(startDate, endDate);
        return ResponseEntity.ok(penalties);
    }

    @GetMapping("/student/{studentId}/pending-total")
    public ResponseEntity<Double> getTotalPendingPenaltiesByStudent(@PathVariable Long studentId) {
        Double total = penaltyService.getTotalPendingPenaltiesByStudent(studentId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/student/{studentId}/has-pending")
    public ResponseEntity<Boolean> hasPendingPenalties(@PathVariable Long studentId) {
        boolean hasPending = penaltyService.hasPendingPenalties(studentId);
        return ResponseEntity.ok(hasPending);
    }
}