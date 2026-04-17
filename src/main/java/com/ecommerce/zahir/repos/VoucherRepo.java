package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.Voucher;
import java.util.Optional;

/**
 * Repository for managing discount vouchers.
 * Used to store and retrieve voucher data by code.
 */

@Repository
public interface VoucherRepo extends JpaRepository<Voucher,Long> {
    
    /**
     * Finds a voucher by its unique code.
     *
     * param code the voucher code entered by the customer
     * return an Optional containing the voucher if found
     */
    Optional<Voucher> findByCode(String code);
}
