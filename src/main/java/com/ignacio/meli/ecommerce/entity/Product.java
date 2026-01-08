package com.ignacio.meli.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 150)
	    private String name;

	    @Column(length = 500)
	    private String description;

	    @Column(nullable = false, precision = 10, scale = 2)
	    private BigDecimal price;

	    @Column(nullable = false)
	    private Integer stock;

	    @Column(nullable = false)
	    private Boolean active;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @PrePersist
	    void prePersist() {
	        this.createdAt = LocalDateTime.now();
	        this.active = true;
	    }
	

}
