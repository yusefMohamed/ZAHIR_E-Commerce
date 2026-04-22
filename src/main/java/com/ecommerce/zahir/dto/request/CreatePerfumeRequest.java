package com.ecommerce.zahir.dto.request;
import com.ecommerce.zahir.enums.GenderCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Request DTO used to create a new perfume product.
 */
@Getter
@Setter
public class CreatePerfumeRequest {
    
    @NotBlank(message = "Perfume name is required")
    @Size(max = 100, message = "Perfume name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Slug is required")
    @Size(max = 120, message = "Slug must not exceed 120 characters")
    private String slug;

    @NotBlank(message = "Short description is required")
    @Size(max = 255, message = "Short description must not exceed 255 characters")
    private String shortDescription;

    @NotBlank(message = "Full description is required")
    private String fullDescription;

    @NotBlank(message = "Name meaning is required")
    private String nameMeaning;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be zero or greater")
    private Integer stockQuantity;

    @NotNull(message = "Gender category is required")
    private GenderCategory genderCategory;

    @NotNull(message = "Hot deal flag is required")
    private Boolean hotDeal;

    @NotNull(message = "Active flag is required")
    private Boolean active;

    @NotNull(message = "Featured flag is required")
    private Boolean featured;

    @NotBlank(message = "Image URL is required")
    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String imageUrl;
}
