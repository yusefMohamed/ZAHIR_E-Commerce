package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO representing a single item in an order.
 */
@Getter
@Setter
public class OrderItemRequest {
    
    @NotNull(message = "Perfume ID is required")
    private Long perfumeId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
