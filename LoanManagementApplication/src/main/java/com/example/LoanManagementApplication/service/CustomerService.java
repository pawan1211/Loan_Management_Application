package com.example.LoanManagementApplication.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LoanManagementApplication.dto.CustomerDTO;
import com.example.LoanManagementApplication.entity.Customer;
import com.example.LoanManagementApplication.repository.CustomerRepository;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer(null, dto.getName(), dto.getEmail(), dto.getContactNumber(), dto.getAddress());
        customer = customerRepository.save(customer);
        dto.setId(customer.getId());
        return dto;
    }

    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getContactNumber(), customer.getAddress());
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setContactNumber(dto.getContactNumber());
        customer.setAddress(dto.getAddress());
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getContactNumber(), customer.getAddress());
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(c ->
                new CustomerDTO(c.getId(), c.getName(), c.getEmail(), c.getContactNumber(), c.getAddress())
        ).collect(Collectors.toList());
    }
}
