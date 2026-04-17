package com.ecommerce.zahir.exceptions;

import com.ecommerce.zahir.enums.ErrorCode;

/**
 * Thrown when a requested resource cannot be found in the system.
 * Examples: user, order, perfume, voucher, or customer not found.
 */

public class ResourceNotFoundException extends RuntimeException  {
 
    private final ErrorCode code;

    public ResourceNotFoundException(ErrorCode code, String message) {
        super(message);
        this.code= code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
