package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Response DTO used to prepare WhatsApp confirmation message for the customer.
 */
@Getter
@Setter
public class OrderConfirmationWhatsappResponse {

    // Customer Info
    private String customerFullName;

    private String primaryPhone;

    // Address
    private String addressLine;

    private String governorate;

    // Order Info
    private String orderNumber;

    private List<OrderConfirmationItemResponse> items;

    // Pricing
    private BigDecimal totalAmount;

    // Delivery Info
    private String estimatedDeliveryMessage;
    
}
