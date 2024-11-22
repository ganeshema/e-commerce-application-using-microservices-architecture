package com.ganeshgc.productservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product Id is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity

) {
}
