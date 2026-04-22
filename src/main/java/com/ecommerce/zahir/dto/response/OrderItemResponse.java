package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Response DTO representing a single item inside an order.
 */
@Getter
@Setter
public class OrderItemResponse {

    private Long id;

    private Long perfumeId;

    private String perfumeName;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal lineTotal;
    
}
