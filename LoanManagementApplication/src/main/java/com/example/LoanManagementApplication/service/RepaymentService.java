package com.example.LoanManagementApplication.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoanManagementApplication.dto.RepaymentDTO;
import com.example.LoanManagementApplication.entity.Loan;
import com.example.LoanManagementApplication.entity.Repayment;
import com.example.LoanManagementApplication.repository.LoanRepository;
import com.example.LoanManagementApplication.repository.RepaymentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    public RepaymentDTO makeRepayment(RepaymentDTO dto) {
        Loan loan = loanRepository.findById(dto.getLoanId()).orElseThrow();
        loan.setRemainingBalance(Math.max(0, loan.getRemainingBalance() - dto.getAmountPaid()));
        if (loan.getRemainingBalance() == 0) {
            loan.setStatus("repaid");
        }
        loanRepository.save(loan);

        Repayment repayment = new Repayment(null, loan, dto.getAmountPaid(), dto.getPaymentDate());
        repayment = repaymentRepository.save(repayment);
        dto.setId(repayment.getId());
        return dto;
    }

    public List<RepaymentDTO> getRepaymentsByLoan(Long loanId) {
        return repaymentRepository.findByLoanId(loanId).stream().map(r ->
                new RepaymentDTO(r.getId(), r.getLoan().getId(), r.getAmountPaid(), r.getPaymentDate())
        ).collect(Collectors.toList());
    }
}

