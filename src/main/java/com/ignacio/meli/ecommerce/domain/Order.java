package com.ignacio.meli.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	   // Relación con el producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // Ej: paid, pending, cancelled
    @Column(name = "order_status")
    private String orderStatus;

    // Ej: ready_to_ship, shipped
    @Column(name = "shipping_status")
    private String shippingStatus;

    @Column(name = "buyer_nickname")
    private String buyerNickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
	

}
