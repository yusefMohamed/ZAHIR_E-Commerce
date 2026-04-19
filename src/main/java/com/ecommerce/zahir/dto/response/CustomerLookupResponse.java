package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO used when searching for a customer by phone number during checkout.
 */
@Getter
@Setter
public class CustomerLookupResponse {

    private boolean found;

    private Long id;

    private String fullName;

    private String primaryPhone;

    private String secondaryPhone;

    private String addressLine;

    private String governorate;
    
}
