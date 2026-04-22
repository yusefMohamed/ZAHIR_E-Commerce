package com.ecommerce.zahir.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ecommerce.zahir.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Lightweight response DTO used for displaying orders in dashboard listings.
 */
@Getter
@Setter

public class OrderSummaryResponse {
    private Long id;

    private String customerFullName;

    private String primaryPhone;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private LocalDateTime createdAt;
}
