package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.Perfume;

import java.util.Optional;

/**
 * Repository for managing perfume products.
 * Used to retrieve product data for both dashboard management and customer-facing pages.
 */

@Repository
public interface PerfumeRepo extends JpaRepository<Perfume,Long> {
    
    /**
     * Finds a perfume by its unique slug.
     * This is mainly used for product details pages.
     *
     *  param slug the perfume slug
     *  return an Optional containing the perfume if found
     */
    Optional<Perfume> findBySlug(String slug);
}
