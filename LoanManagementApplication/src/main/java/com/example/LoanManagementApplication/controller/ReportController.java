package com.example.LoanManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LoanManagementApplication.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/loan-history/{customerId}")
    public Object getLoanHistory(@PathVariable Long customerId) {
        return reportService.getLoanHistoryByCustomer(customerId);
    }

    @GetMapping("/pending-loans")
    public Object getPendingLoansReport() {
        return reportService.getPendingLoansReport();
    }
}
