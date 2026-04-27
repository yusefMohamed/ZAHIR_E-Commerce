package com.ecommerce.zahir.enums;

/**
 * Represents standardized error codes used across the application.
 * These codes help identify error types programmatically.
 */

public enum ErrorCode {

    // General
    INTERNAL_ERROR,

    // Not Found
    USER_NOT_FOUND,
    ROLE_NOT_FOUND,
    CUSTOMER_NOT_FOUND,
    ORDER_NOT_FOUND,
    PERFUME_NOT_FOUND,
    VOUCHER_NOT_FOUND,

    // Business
    INVALID_VOUCHER,
    OUT_OF_STOCK,
    INVALID_ORDER_STATE,
    DUPLICATE_RESOURCE,
    INVALID_ROLE,
    PASSWORD_MISMATCH,

    UNAUTHORIZED_ACTION,
    SELF_DEACTIVATION_NOT_ALLOWED,
    MANAGER_ROLE_PROTECTED
}
