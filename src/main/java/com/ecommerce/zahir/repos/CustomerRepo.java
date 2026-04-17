package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.Customer;
import java.util.Optional;

/**
 * Repository for managing customer data.
 * Customers are mainly identified by their primary phone number
 * during the checkout process.
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    /**
     * Finds a customer by primary phone number.
     * This is used to identify returning customers during checkout.
     *
     * param primaryPhone the customer's main phone number
     * return an Optional containing the customer if found
     */
    Optional<Customer> findByPrimaryPhone(String primaryPhone);
}
