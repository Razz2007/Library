package com.racinger.librarySystem.Library.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDto {

    private Long id;
    private Long loanId;
    private LocalDate returnDate;
    private Integer daysLate;
    private Double penaltyAmount;
    private String notes;
}