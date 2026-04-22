package com.ecommerce.zahir.dto.request;

import com.ecommerce.zahir.enums.VoucherType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO used to update an existing voucher.
 */
@Getter
@Setter
public class UpdateVoucherRequest {
    
    @NotBlank(message = "Voucher code is required")
    @Size(max = 50, message = "Voucher code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Voucher type is required")
    private VoucherType voucherType;

    @NotNull(message = "Discount value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount value must be greater than zero")
    private BigDecimal discountValue;

    @NotNull(message = "Active flag is required")
    private Boolean active;

    @NotNull(message = "End date is required")
    private LocalDate endDate;
}
