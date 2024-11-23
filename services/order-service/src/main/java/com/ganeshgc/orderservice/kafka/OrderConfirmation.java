package com.ganeshgc.orderservice.kafka;

import com.ganeshgc.orderservice.customer.CustomerResponse;
import com.ganeshgc.orderservice.order.PaymentMethod;
import com.ganeshgc.orderservice.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

){

}
