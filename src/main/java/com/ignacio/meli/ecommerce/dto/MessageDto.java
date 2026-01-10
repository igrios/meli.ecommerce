package com.ignacio.meli.ecommerce.dto;



public record MessageDto(
        String meliMessageId,
        String buyerId,
        Long productId,
        Long orderId,
        String messageText
) {
}