package com.ecommerce.zahir.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a single product line inside an order.
 * Each order item stores product snapshot data to preserve order history
 * even if the original perfume data changes later.
 */

@Entity
@Table(name = "order_items")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the order that owns this item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Reference to the original perfume product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id", nullable = false)
    private Perfume perfume;

    // Snapshot of perfume name at the moment of checkout
    @Column(nullable = false, length = 100)
    private String perfumeNameSnapshot;

    // Unit price at the moment of checkout
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    // Ordered quantity of this perfume
    @Column(nullable = false)
    private Integer quantity;

    // Total amount for this order line = unitPrice * quantity
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal lineTotal;


    
}
