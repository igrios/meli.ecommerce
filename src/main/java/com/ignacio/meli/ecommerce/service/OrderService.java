package com.ignacio.meli.ecommerce.service;

import com.ignacio.meli.ecommerce.domain.*;
import com.ignacio.meli.ecommerce.dto.OrderItemRequest;
import com.ignacio.meli.ecommerce.repository.OrderItemRepository;
import com.ignacio.meli.ecommerce.repository.OrderRepository;
import com.ignacio.meli.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public Order createOrder(String meliOrderId, String buyerId, List<OrderItemRequest> items) {

        Order order = Order.builder()
                .meliOrderId(meliOrderId)
                .buyerId(buyerId)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        order = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest req : items) {

            Product product = productRepository.findById(req.productId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (product.getStock() < req.quantity()) {
                throw new RuntimeException("Stock insuficiente para " + product.getTitle());
            }

            BigDecimal lineTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(req.quantity()));

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(req.quantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(lineTotal)
                    .build();

            orderItems.add(item);

            product.setStock(product.getStock() - req.quantity());
            productRepository.save(product);

            total = total.add(lineTotal);
        }

        orderItemRepository.saveAll(orderItems);

        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PAID);

        return orderRepository.save(order);
    }
}
