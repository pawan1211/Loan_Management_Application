package com.example.LoanManagementApplication.dto;



import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentDTO {
 private Long id;
 private Long loanId;
 private double amountPaid;
 private LocalDate paymentDate;
}
