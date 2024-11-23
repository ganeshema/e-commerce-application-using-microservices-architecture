package com.ganeshgc.paymentservice.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "First name is required.")
        String firstname,
        @NotNull(message = "Last name is required.")
        String lastname,
        @NotNull(message = "Email is required.")
        @Email(message = "please enter the valid email id.")
        String email

) {
}
