package com.example.LoanManagementApplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LoanManagementApplication.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
