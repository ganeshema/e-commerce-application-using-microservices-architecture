package com.ganeshgc.orderservice.payment;

import com.ganeshgc.orderservice.customer.CustomerResponse;
import com.ganeshgc.orderservice.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
