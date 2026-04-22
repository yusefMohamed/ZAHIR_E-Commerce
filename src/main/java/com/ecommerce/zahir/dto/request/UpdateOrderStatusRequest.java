package com.ecommerce.zahir.dto.request;

import com.ecommerce.zahir.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to update the status of an order.
 */
@Getter
@Setter
public class UpdateOrderStatusRequest {
    
    @NotNull(message = "Order status is required")
    private OrderStatus status;
}
