package com.racinger.librarySystem.Library.Controller;

import com.racinger.librarySystem.Library.Entity.Return;
import com.racinger.librarySystem.Library.Service.interfaces.IReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/returns")
@RequiredArgsConstructor
public class ReturnController {

    private final IReturnService returnService;

    @GetMapping
    public ResponseEntity<List<Return>> getAllReturns() {
        List<Return> returns = returnService.findAll();
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
        Optional<Return> returnEntity = returnService.findById(id);
        return returnEntity.map(ResponseEntity::ok)
                           .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Return> processReturn(@RequestParam Long loanId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        try {
            Return returnEntity = returnService.processReturn(loanId, returnDate);
            return ResponseEntity.ok(returnEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<Return>> getReturnsByLoan(@PathVariable Long loanId) {
        List<Return> returns = returnService.findReturnsByLoan(loanId);
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Return>> getReturnsInDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Return> returns = returnService.findReturnsInDateRange(startDate, endDate);
        return ResponseEntity.ok(returns);
    }

    @GetMapping("/calculate-penalty")
    public ResponseEntity<Double> calculatePenalty(@RequestParam int daysLate) {
        double penalty = returnService.calculatePenaltyAmount(daysLate);
        return ResponseEntity.ok(penalty);
    }

    @GetMapping("/check-late")
    public ResponseEntity<Boolean> checkIfReturnIsLate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        boolean isLate = returnService.isReturnLate(dueDate, returnDate);
        return ResponseEntity.ok(isLate);
    }
}