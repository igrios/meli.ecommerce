package com.ignacio.meli.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



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

    // ID real de Mercado Libre
    @Column(name = "meli_order_id", unique = true)
    private String meliOrderId;

    @Column(name = "buyer_id")
    private String buyerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "shipping_status")
    private String shippingStatus;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}