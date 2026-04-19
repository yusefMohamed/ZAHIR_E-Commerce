package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to look up an existing customer by primary phone number.
 */
@Getter
@Setter
public class CustomerLookupRequest {
    
    @NotBlank(message = "Primary phone is required")
    @Pattern(
            regexp = "^01[0125][0-9]{8}$",
            message = "Phone must be a valid Egyptian mobile number"
    )
    private String primaryPhone;
}
