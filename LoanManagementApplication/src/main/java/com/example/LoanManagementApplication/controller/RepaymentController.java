package com.example.LoanManagementApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LoanManagementApplication.dto.RepaymentDTO;
import com.example.LoanManagementApplication.service.RepaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;

    @PostMapping
    public RepaymentDTO makeRepayment(@RequestBody RepaymentDTO dto) {
        return repaymentService.makeRepayment(dto);
    }

    @GetMapping("/loan/{loanId}")
    public List<RepaymentDTO> getRepaymentsByLoan(@PathVariable Long loanId) {
        return repaymentService.getRepaymentsByLoan(loanId);
    }
}
