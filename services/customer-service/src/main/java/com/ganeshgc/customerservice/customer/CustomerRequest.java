package com.ganeshgc.customerservice.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record CustomerRequest(
        String id,
        @NotNull(message="Customer's first name is required")
        String firstname,
        @NotNull(message="Customer's last name is required")
        String lastname,
        @NotNull(message="Customer's  email is required")
        @Email(message = "Customer's Email is not valid")
        String email,
        Address address
) {}
