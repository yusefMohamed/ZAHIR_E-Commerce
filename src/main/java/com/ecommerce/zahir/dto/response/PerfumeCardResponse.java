package com.ecommerce.zahir.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.ecommerce.zahir.enums.GenderCategory;

/**
 * Lightweight response DTO used for displaying perfume cards in listings.
 */
@Getter
@Setter
public class PerfumeCardResponse {
    
    private Long id;

    private String name;

    private String slug;

    private BigDecimal price;

    private String imageUrl;

    private GenderCategory genderCategory;

    private boolean hotDeal;

    private boolean featured;
}
