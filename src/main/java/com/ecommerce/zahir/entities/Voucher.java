package com.ecommerce.zahir.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ecommerce.zahir.enums.VoucherType;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a discount voucher that can be applied to orders.
 * A voucher can be fixed-value or percentage-based.
 */

@Entity
@Table(name = "vouchers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique code entered during checkout
    @Column(nullable = false, unique = true, length = 50)
    private String code;
    
    // Defines how the discount value should be interpreted
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private VoucherType voucherType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.active == null) {
            this.active = true;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
