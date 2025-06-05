package com.example.LoanManagementApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoanManagementApplication.entity.Customer;
import com.example.LoanManagementApplication.entity.Loan;
import com.example.LoanManagementApplication.repository.CustomerRepository;
import com.example.LoanManagementApplication.repository.LoanRepository;
import com.example.LoanManagementApplication.repository.RepaymentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RepaymentRepository repaymentRepository;

    public Map<String, Object> getLoanHistoryByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        List<Loan> loans = loanRepository.findByCustomerId(customerId);

        List<Map<String, Object>> loanDetails = new ArrayList<>();
        for (Loan loan : loans) {
            Map<String, Object> loanMap = new HashMap<>();
            loanMap.put("loanId", loan.getId());
            loanMap.put("amount", loan.getAmount());
            loanMap.put("status", loan.getStatus());
            loanMap.put("repayments", repaymentRepository.findByLoanId(loan.getId()).stream().map(r ->
                    Map.of("amountPaid", r.getAmountPaid(), "paymentDate", r.getPaymentDate())
            ).collect(Collectors.toList()));
            loanDetails.add(loanMap);
        }

        return Map.of(
                "customerId", customer.getId(),
                "customerName", customer.getName(),
                "loans", loanDetails
        );
    }

    public List<Map<String, Object>> getPendingLoansReport() {
        List<Loan> loans = loanRepository.findByStatus("pending");

        return loans.stream().map(loan -> {
            Map<String, Object> map = new HashMap<>();
            map.put("loanId", loan.getId());
            map.put("customerName", loan.getCustomer().getName());
            map.put("amount", loan.getAmount());
            map.put("status", loan.getStatus());
            map.put("remainingBalance", loan.getRemainingBalance());
            map.put("dueDate", loan.getDueDate());
            return map;
        }).collect(Collectors.toList());
    }
}
