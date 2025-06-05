package com.example.LoanManagementApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
 private Long id;
 private Long customerId;
 private double amount;
 private double interestRate;
 private int durationMonths;
 private String purpose;
 private String status;
 private double remainingBalance;
}
