package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to collect customer data during checkout.
 */
@Getter
@Setter

public class CheckoutCustomerRequest {
    
    @NotBlank(message = "Full name is required")
    @Size(max = 150, message = "Full name must not exceed 150 characters")
    private String fullName;

    @NotBlank(message = "Primary phone is required")
    @Pattern(
            regexp = "^01[0125][0-9]{8}$",
            message = "Primary phone must be a valid Egyptian mobile number"
    )
    private String primaryPhone;

    @Pattern(
            regexp = "^$|^01[0125][0-9]{8}$",
            message = "Secondary phone must be a valid Egyptian mobile number"
    )
    private String secondaryPhone;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String addressLine;

    @NotBlank(message = "Governorate is required")
    @Size(max = 100, message = "Governorate must not exceed 100 characters")
    private String governorate;
}
