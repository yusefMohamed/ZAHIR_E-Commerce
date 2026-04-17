package com.ecommerce.zahir.exceptions;

import com.ecommerce.zahir.enums.ErrorCode;

/**
 * Thrown when a business rule is violated during application flow.
 * Examples: invalid voucher, out of stock, or invalid order state.
 */

public class BusinessException extends RuntimeException {
    
    private final ErrorCode code;

    public BusinessException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
