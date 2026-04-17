package com.ecommerce.zahir.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a standardized error response returned to the client.
 */
@Getter
@AllArgsConstructor

public class ErrorResponse {

    private String code;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    
}
