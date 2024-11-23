package com.ganeshgc.orderservice.order;

import com.ganeshgc.orderservice.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount should be positive.")
        BigDecimal amount,
        @NotNull(message = "you must select the payment method")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer should be present")
        @NotEmpty(message = "customer should be present")
        @NotBlank(message = "customer should be present")
        String customerId,
        @NotEmpty(message = "You should alteast purchase one product")
        List<PurchaseRequest> products
) {
}
