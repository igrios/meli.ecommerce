package com.ignacio.meli.ecommerce.controller;

import com.ignacio.meli.ecommerce.domain.Order;
import com.ignacio.meli.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
	
	 private final OrderService orderService;

	    @PostMapping
	    public Order createOrder(@RequestBody Order order) {
	        return orderService.CreateOrder(order);
	    }

}
