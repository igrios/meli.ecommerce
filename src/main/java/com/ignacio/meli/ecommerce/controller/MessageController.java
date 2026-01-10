package com.ignacio.meli.ecommerce.controller;

import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.Order;
import com.ignacio.meli.ecommerce.domain.Product;
import com.ignacio.meli.ecommerce.dto.MessageDto;
import com.ignacio.meli.ecommerce.repository.OrderRepository;
import com.ignacio.meli.ecommerce.repository.ProductRepository;
import com.ignacio.meli.ecommerce.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto dto) {

        Product product = null;
        Order order = null;

        if (dto.productId() != null) {
            product = productRepository.findById(dto.productId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        }

        if (dto.orderId() != null) {
            order = orderRepository.findById(dto.orderId())
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        }

        Message message = Message.builder()
                .meliMessageId(dto.meliMessageId())
                .buyerId(dto.buyerId())
                .messageText(dto.messageText())
                .product(product)
                .order(order)
                .build();

        Message saved = messageService.receiveMessage(message);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}
