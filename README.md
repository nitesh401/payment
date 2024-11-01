# Payment API

A Spring Boot-based REST API for handling payment processing. This API supports multiple payment modes (UPI, NetBanking, DebitCard) and follows a layered architecture with validation, processing, and logging functionalities.

## Features
- CRUD operations for payments
- Validation for email format and payment mode
- Prevents duplicate payment entries by email
- Supports multiple payment modes using a strategy pattern
- Logging for monitoring and debugging
- RESTful endpoints for managing payments

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (for development/testing)
- Jackson (for object mapping)
- SLF4J with Logback (for logging)

## Prerequisites
- Java 17
- Maven 3.6 or higher
- Postman (optional, for testing)

## Build The Application 
- mvn clean install

## Run The Application
- mvn spring-boot:run


## Getting Started

### 1. Clone the repository
```bash
git clone <repository-url>
cd payment-api

