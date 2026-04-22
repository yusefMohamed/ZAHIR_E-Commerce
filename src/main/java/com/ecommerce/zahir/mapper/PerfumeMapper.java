package com.ecommerce.zahir.mapper;

import com.ecommerce.zahir.dto.request.CreatePerfumeRequest;
import com.ecommerce.zahir.dto.response.PerfumeCardResponse;
import com.ecommerce.zahir.dto.response.PerfumeResponse;
import com.ecommerce.zahir.entities.Perfume;

/**
 * Mapper class responsible for converting between Perfume entity and DTOs.
 */
public class PerfumeMapper {

    /**
     * Convert CreatePerfumeRequest → Perfume entity
     */
    public static Perfume toEntity(CreatePerfumeRequest request) {

        Perfume perfume = new Perfume();

        perfume.setName(request.getName());
        perfume.setSlug(request.getSlug());
        perfume.setShortDescription(request.getShortDescription());
        perfume.setFullDescription(request.getFullDescription());
        perfume.setNameMeaning(request.getNameMeaning());
        perfume.setPrice(request.getPrice());
        perfume.setStockQuantity(request.getStockQuantity());
        perfume.setGenderCategory(request.getGenderCategory());
        perfume.setHotDeal(request.getHotDeal());
        perfume.setActive(request.getActive());
        perfume.setFeatured(request.getFeatured());
        perfume.setImageUrl(request.getImageUrl());

        return perfume;
    }

    /**
     * Convert Perfume → PerfumeResponse (full details)
     */
    public static PerfumeResponse toResponse(Perfume perfume) {
        PerfumeResponse response = new PerfumeResponse();

        response.setId(perfume.getId());
        response.setName(perfume.getName());
        response.setSlug(perfume.getSlug());
        response.setShortDescription(perfume.getShortDescription());
        response.setFullDescription(perfume.getFullDescription());
        response.setNameMeaning(perfume.getNameMeaning());
        response.setPrice(perfume.getPrice());
        response.setStockQuantity(perfume.getStockQuantity());
        response.setGenderCategory(perfume.getGenderCategory());
        response.setHotDeal(Boolean.TRUE.equals(perfume.getHotDeal()));
        response.setActive(Boolean.TRUE.equals(perfume.getActive()));
        response.setFeatured(Boolean.TRUE.equals(perfume.getFeatured()));
        response.setImageUrl(perfume.getImageUrl());
        response.setCreatedAt(perfume.getCreatedAt());
        response.setUpdatedAt(perfume.getUpdatedAt());

        return response;
    }

    /**
     * Convert Perfume → PerfumeCardResponse (lightweight for listings)
     */
    public static PerfumeCardResponse toCardResponse(Perfume perfume) {
        PerfumeCardResponse response = new PerfumeCardResponse();

        response.setId(perfume.getId());
        response.setName(perfume.getName());
        response.setSlug(perfume.getSlug());
        response.setPrice(perfume.getPrice());
        response.setImageUrl(perfume.getImageUrl());
        response.setGenderCategory(perfume.getGenderCategory());
        response.setHotDeal(Boolean.TRUE.equals(perfume.getHotDeal()));
        response.setFeatured(Boolean.TRUE.equals(perfume.getFeatured()));

        return response;
    }
}
