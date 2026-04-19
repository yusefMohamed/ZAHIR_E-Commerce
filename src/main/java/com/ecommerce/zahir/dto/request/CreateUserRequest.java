package com.ecommerce.zahir.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used to create a new dashboard user.
 */
@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "First name is Required")
    @Size(max = 50, message = "First Name Must Not Exceed 50 Characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "^01[0125][0-9]{8}$", 
        message = "Phone must be a valid Egyptian mobile number"
    )
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;

}
