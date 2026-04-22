package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.Order;
import java.util.Optional;

/**
 * Repository for managing customer orders.
 * Used to store and retrieve placed orders during checkout and dashboard operations.
 */

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
 /**
     * Finds an order by its unique business order number.
     *
     * param orderNumber the order number displayed to the customer and dashboard
     * return an Optional containing the order if found
     */
    Optional<Order> findByOrderNumber(String orderNumber);   

    long countByCustomerId(Long customerId);
}
