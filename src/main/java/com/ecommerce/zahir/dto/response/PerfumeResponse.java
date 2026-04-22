package com.ecommerce.zahir.dto.response;

import com.ecommerce.zahir.enums.GenderCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Response DTO used to return full perfume details.
 */
@Getter
@Setter
public class PerfumeResponse {
    
    private Long id;

    private String name;

    private String slug;

    private String shortDescription;

    private String fullDescription;

    private String nameMeaning;

    private BigDecimal price;

    private Integer stockQuantity;

    private GenderCategory genderCategory;

    private boolean hotDeal;

    private boolean active;

    private boolean featured;

    private String imageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
