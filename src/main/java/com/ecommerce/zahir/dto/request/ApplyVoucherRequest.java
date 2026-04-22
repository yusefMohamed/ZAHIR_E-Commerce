package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to apply a voucher during checkout.
 */
@Getter
@Setter
public class ApplyVoucherRequest {
 
    @NotBlank(message = "Voucher code is required")
    @Size(max = 50, message = "Voucher code must not exceed 50 characters")
    private String code;
}
