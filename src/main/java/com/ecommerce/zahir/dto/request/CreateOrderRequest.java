package com.ecommerce.zahir.dto.request;

import com.ecommerce.zahir.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * Request DTO used to create a new order during checkout.
 */
@Getter
@Setter
public class CreateOrderRequest {

    @NotNull(message = "Customer data is required")
    @Valid
    private CheckoutCustomerRequest customer;

    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItemRequest> items;

    private String voucherCode;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private String notes;
    
}
