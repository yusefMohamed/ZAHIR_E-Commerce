package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Response DTO representing a single item in WhatsApp order confirmation.
 */
@Getter
@Setter
public class OrderConfirmationItemResponse {

    private String perfumeName;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;
    
}
