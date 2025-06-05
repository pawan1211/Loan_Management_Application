package com.example.LoanManagementApplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
}