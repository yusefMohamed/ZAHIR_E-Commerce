package com.ecommerce.zahir.dto.response;

import com.ecommerce.zahir.enums.VoucherType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Response DTO used to return voucher data.
 */
@Getter
@Setter
public class VoucherResponse {

    private Long id;

    private String code;

    private VoucherType voucherType;

    private BigDecimal discountValue;

    private boolean active;

    private LocalDate startDate;

    private LocalDate endDate;
    
}
