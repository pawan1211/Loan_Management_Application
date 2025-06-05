package com.example.LoanManagementApplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LoanManagementApplication.dto.LoanDTO;
import com.example.LoanManagementApplication.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public LoanDTO applyLoan(@RequestBody LoanDTO dto) {
        return loanService.applyLoan(dto);
    }

//    @GetMapping("/{id}")
//    public LoanDTO getLoan(@PathVariable Long id) {
//        return loanService.getLoan(id);
//    }

    @GetMapping("/customer/{customerId}")
    public List<LoanDTO> getCustomerLoans(@PathVariable Long customerId) {
        return loanService.getLoansByCustomer(customerId);
    }

    @GetMapping("/pending")
    public List<LoanDTO> getPendingLoans() {
        return loanService.getPendingLoans();
    }
    
    
    @PutMapping("/mark-overdue")
    public void markOverdueLoans() {
        loanService.markOverdueLoans();
    }
    
    
}