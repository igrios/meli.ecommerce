package com.ignacio.meli.ecommerce.controller;

import com.ignacio.meli.ecommerce.domain.Order;
//import com.ignacio.meli.ecommerce.dto.OrderItemRequest;
import com.ignacio.meli.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {

        Order order = orderService.createOrder(
                request.meliOrderId(),
                request.buyerId(),
                request.items()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
