package com.ganeshgc.orderservice.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
