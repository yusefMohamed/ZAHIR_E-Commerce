package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO used to return dashboard user data safely to the client.
 */
@Getter
@Setter
public class UserResponse {
     private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String role;

    private boolean enabled;
}
