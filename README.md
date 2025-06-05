# Loan_Management_Application

# 💰 Loan Management System Backend (Spring Boot + JPA + MySQL/PostgreSQL)

This is a full-featured backend system for managing **customer loans**, **repayments**, **loan statuses**, and **basic reporting**, built using **Java**, **Spring Boot**, **Spring Data JPA (Hibernate)**, and **MySQL/PostgreSQL**.

---

## 🚀 Features Implemented

### 1. 👤 Customer Management
- **Add New Customers:** Register customers with name, email, contact number, and address.
- **Update & View:** Modify or fetch customer details using customer ID.

### 2. 📝 Loan Application
- **Apply for Loan:** Customers can request loans by providing:
  - Loan Amount
  - Interest Rate
  - Duration (in months)
  - Purpose
- **Loan Approval Logic:**
  - If loan amount > ₹5,00,000 → status = `"pending"`
  - Else → status = `"approved"`
- **Interest Calculation:**
  - Formula: `Simple Interest = (P × R × T) / 100` (Annualized)
  - Total repayable = Principal + Interest

### 3. 💸 Loan Repayment
- **Track Repayments:** Record partial/full repayments with payment date.
- **Loan Balance Tracking:** Automatically updates `remainingBalance`.
- **Loan Repayment Completion:** Status updates to `"repaid"` if balance hits 0.
- **Overdue Loans:**
  - Scheduled job runs daily.
  - If due date has passed **and** balance > 0 → status = `"overdue"`

### 4. 📊 Loan Status & Reports
- **Loan Status Tracking:**
  - `"pending"`, `"approved"`, `"repaid"`, `"overdue"`
- **Reports:**
  - 🔍 **Loan History** by Customer: Includes all loans + repayments.
  - 📌 **Pending Loans Report:** All loans still pending approval or repayment.

---

## 🛠️ Tech Stack

| Technology       | Description                             |
|------------------|-----------------------------------------|
| Java             | Programming Language                    |
| Spring Boot      | REST API + Dependency Management        |
| Spring Data JPA  | ORM with Hibernate                      |
| MySQL/PostgreSQL | Relational Database                     |
| Lombok           | Reduces boilerplate code                |
| Postman          | API testing                             |  

---

## 📂 Project Structure

```bash
src/
├── controller
│   ├── CustomerController.java
│   ├── LoanController.java
│   ├── RepaymentController.java
│   └── ReportController.java
├── dto
│   ├── CustomerDTO.java
│   ├── LoanDTO.java
│   └── RepaymentDTO.java
├── entity
│   ├── Customer.java
│   ├── Loan.java
│   ├── Repayment.java
│   
├── repository
│   ├── CustomerRepository.java
│   ├── LoanRepository.java
│   └── RepaymentRepository.java
├── service
│   ├── CustomerService.java
│   ├── LoanService.java
│   ├── RepaymentService.java
│   └── ReportService.java
