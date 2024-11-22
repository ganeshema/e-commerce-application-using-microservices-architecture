package com.ganeshgc.productservice.product;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;



public record ProductRequest (
         Integer id,
         @NotNull(message = "Product name is required.")
         String name,
         @NotNull(message = "Product description is required.")
         String description,
         @Positive(message = "available quantity should be positive.")
         Double availableQuantity,
         @Positive(message = "Price quantity should be positive.")
         BigDecimal price,
         @NotNull(message = "Product category is required.")
         Integer categoryId
)
{ }
