package com.ignacio.meli.ecommerce.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Mercado Libre =====
    @Column(name = "meli_item_id", unique = true)
    private String meliItemId;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    // ===== Pricing =====
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "min_margin", precision = 5, scale = 2)
    private BigDecimal minMargin;

    // ===== Stock =====
    @Column(nullable = false)
    private Integer stock;

    // ===== Estado ML =====
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    // ===== Auditoría =====
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== Ciclo de vida =====
    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}