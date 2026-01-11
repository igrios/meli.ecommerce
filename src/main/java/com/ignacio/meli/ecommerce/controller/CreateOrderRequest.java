package com.ignacio.meli.ecommerce.controller;

import java.util.List;

import com.ignacio.meli.ecommerce.dto.OrderItemRequest;

public record CreateOrderRequest(String meliOrderId,
        String buyerId,
        List<OrderItemRequest> items) {

}
