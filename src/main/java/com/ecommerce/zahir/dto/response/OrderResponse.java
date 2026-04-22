package com.ecommerce.zahir.dto.response;

import com.ecommerce.zahir.enums.OrderStatus;
import com.ecommerce.zahir.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Response DTO used to return full order details.
 */
@Getter
@Setter
public class OrderResponse {

    private Long id;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    // Customer Info
    private Long customerId;

    private String customerFullName;

    private String primaryPhone;

    private String addressLine;

    private String governorate;

    // Voucher
    private String voucherCode;

    // Pricing
    private BigDecimal subtotal;

    private BigDecimal discountAmount;

    private BigDecimal totalAmount;

    // Items
    private List<OrderItemResponse> items;

    // Metadata
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
