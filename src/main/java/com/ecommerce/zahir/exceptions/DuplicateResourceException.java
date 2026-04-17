package com.ecommerce.zahir.exceptions;
import com.ecommerce.zahir.enums.ErrorCode;

/**
 * Thrown when trying to create or save a resource that already exists.
 * Examples: duplicate email, phone number, slug, or voucher code.
 */
public class DuplicateResourceException extends RuntimeException {
    
    private final ErrorCode code;

    public DuplicateResourceException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
