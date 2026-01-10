package com.ignacio.meli.ecommerce.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;


import com.ignacio.meli.ecommerce.domain.Order;
import com.ignacio.meli.ecommerce.domain.Product;
import com.ignacio.meli.ecommerce.repository.OrderRepository;
import com.ignacio.meli.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductRepository productrepository;
	
	public Order CreateOrder(Order order) {
		
		Product product = productrepository.findById(order.getProduct().getId()).orElseThrow(()-> new RuntimeException("Product no found"));
	
		if(product.getStock() < order.getQuantity()) {
			throw new RuntimeException("No hay  stock");
						
		}
		
		
		// Actualizar stock
        product.setStock(product.getStock()-order.getQuantity());
        productrepository.save(product);
		
     // Completar datos del pedido
        order.setUnitPrice(product.getPrice());
        order.setTotalPrice(product.getPrice()
                .multiply(java.math.BigDecimal.valueOf(order.getQuantity())));
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
		
	}
	
	
}


