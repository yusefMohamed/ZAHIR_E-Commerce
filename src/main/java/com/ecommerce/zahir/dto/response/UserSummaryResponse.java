package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO used to return a lightweight summary of dashboard users.
 */
@Getter
@Setter
public class UserSummaryResponse {
    
    private Long id;

    private String fullName;

    private String email;

    private String role;

    private boolean enabled;
}
