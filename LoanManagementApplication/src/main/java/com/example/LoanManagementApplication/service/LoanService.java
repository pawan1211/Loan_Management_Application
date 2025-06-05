package com.example.LoanManagementApplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoanManagementApplication.dto.LoanDTO;
import com.example.LoanManagementApplication.entity.Customer;
import com.example.LoanManagementApplication.entity.Loan;
import com.example.LoanManagementApplication.repository.CustomerRepository;
import com.example.LoanManagementApplication.repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public LoanDTO applyLoan(LoanDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        String status = dto.getAmount() > 500000 ? "pending" : "approved";
        double interest = (dto.getAmount() * dto.getInterestRate() * dto.getDurationMonths()) / (12 * 100);
        double totalRepay = dto.getAmount() + interest;
        LocalDate dueDate = LocalDate.now().plusMonths(dto.getDurationMonths());
        
        Loan loan = new Loan(null, customer, dto.getAmount(), dto.getInterestRate(), dto.getDurationMonths(), dto.getPurpose(), status, totalRepay, dueDate);
      
        loan = loanRepository.save(loan);
        dto.setId(loan.getId());
        dto.setStatus(loan.getStatus());
        dto.setRemainingBalance(loan.getRemainingBalance());
        return dto;
    }

    public void markOverdueLoans() {
        List<Loan> loans = loanRepository.findByStatus("approved");
        for (Loan loan : loans) {
            if (loan.getDueDate().isBefore(LocalDate.now()) && loan.getRemainingBalance() > 0) {
                loan.setStatus("overdue");
                loanRepository.save(loan);
            }
        }
    }

    public List<LoanDTO> getLoansByCustomer(Long customerId) {
        return loanRepository.findByCustomerId(customerId).stream().map(loan ->
                new LoanDTO(loan.getId(), loan.getCustomer().getId(), loan.getAmount(), loan.getInterestRate(), loan.getDurationMonths(), loan.getPurpose(), loan.getStatus(), loan.getRemainingBalance())
        ).collect(Collectors.toList());
    }

    public List<LoanDTO> getPendingLoans() {
        return loanRepository.findByStatus("pending").stream().map(loan ->
                new LoanDTO(loan.getId(), loan.getCustomer().getId(), loan.getAmount(), loan.getInterestRate(), loan.getDurationMonths(), loan.getPurpose(), loan.getStatus(), loan.getRemainingBalance())
        ).collect(Collectors.toList());
    }
}