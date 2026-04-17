package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.OrderItem;
/**
 * Repository for managing order item records.
 * In the current version, the default JPA operations are enough.
 */

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
    
}
