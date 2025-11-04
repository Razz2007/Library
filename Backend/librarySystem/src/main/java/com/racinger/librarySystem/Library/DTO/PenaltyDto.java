package com.racinger.librarySystem.Library.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyDto {

    private Long id;
    private Long studentId;
    private String studentName;
    private Double amount;
    private String reason;
    private LocalDate issuedDate;
    private LocalDate paidDate;
    private String status;
}