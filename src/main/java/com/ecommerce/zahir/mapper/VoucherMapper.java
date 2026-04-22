package com.ecommerce.zahir.mapper;

import com.ecommerce.zahir.dto.request.CreateVoucherRequest;
import com.ecommerce.zahir.dto.response.VoucherResponse;
import com.ecommerce.zahir.entities.Voucher;

/**
 * Mapper class responsible for converting between Voucher entity and DTOs.
 */
public class VoucherMapper {

    /**
     * Convert CreateVoucherRequest → Voucher entity
     */
    public static Voucher toEntity(CreateVoucherRequest request) {
        Voucher voucher = new Voucher();

        voucher.setCode(request.getCode());
        voucher.setVoucherType(request.getVoucherType());
        voucher.setDiscountValue(request.getDiscountValue());
        voucher.setActive(request.getActive());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());

        return voucher;
    }

    /**
     * Convert Voucher → VoucherResponse
     */
    public static VoucherResponse toResponse(Voucher voucher) {
        VoucherResponse response = new VoucherResponse();

        response.setId(voucher.getId());
        response.setCode(voucher.getCode());
        response.setVoucherType(voucher.getVoucherType());
        response.setDiscountValue(voucher.getDiscountValue());
        response.setActive(Boolean.TRUE.equals(voucher.getActive()));
        response.setStartDate(voucher.getStartDate());
        response.setEndDate(voucher.getEndDate());

        return response;
    }
    
}
