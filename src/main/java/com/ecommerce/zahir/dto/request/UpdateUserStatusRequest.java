package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to update a dashboard user's account status.
 */
@Getter
@Setter
public class UpdateUserStatusRequest {

    @NotNull(message = "Enabled status is required")
    private Boolean enabled;

    @NotNull(message = "Account lock status is required")
    private Boolean accountNonLocked;
    
}
