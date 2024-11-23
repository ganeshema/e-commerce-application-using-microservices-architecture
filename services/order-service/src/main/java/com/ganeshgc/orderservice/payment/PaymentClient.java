package com.ganeshgc.orderservice.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "payment-service",
        url = "{application.config.payment-url}"

)
public interface PaymentClient {
    @PostMapping
    Integer requestOrderPayment(PaymentRequest paymentRequest);
}
