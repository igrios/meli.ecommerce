package com.ignacio.meli.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ignacio.meli.ecommerce.domain.Message;

@Service
public class WhatsappNotificationService {

    public void notifyHuman(Message message) {

        // Por ahora lo simulamos con log
        System.out.println("📲 WHATSAPP ALERT");
        System.out.println("Cliente: " + message.getBuyerId());
        System.out.println("Mensaje: " + message.getMessageText());
        System.out.println("Producto ID: " + 
            (message.getProduct() != null ? message.getProduct().getId() : "N/A"));
    }
}
