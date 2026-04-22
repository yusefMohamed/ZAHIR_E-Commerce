package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Response DTO used to return detailed customer data for dashboard views.
 */

@Setter
@Getter
public class CustomerResponse {
    private Long id;

    private String fullName;

    private String primaryPhone;

    private String secondaryPhone;

    private String addressLine;

    private String governorate;

    private long ordersCount;

    private boolean returningCustomer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
