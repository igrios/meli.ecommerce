package com.ignacio.meli.ecommerce.service;

import org.springframework.stereotype.Service;
import com.ignacio.meli.ecommerce.domain.Message;

@Service
public class MessageReplyService {

    public String generateReply(Message message) {

        String text = message.getMessageText().toLowerCase();

        if (text.contains("precio") || text.contains("cuesta") || text.contains("valor")) {
            return "Hola 👋 El precio del producto es $" 
                    + message.getProduct().getPrice() 
                    + ". Si querés comprarlo, decime y te ayudo 😊";
        }

        if (text.contains("stock")) {
            return "Sí 😊 Tenemos stock disponible del producto.";
        }

        if (text.contains("envio") || text.contains("envío")) {
            return "Hacemos envíos a todo el país por Mercado Envíos 🚚";
        }

        return "Gracias por tu consulta 😊 ¿En qué más puedo ayudarte?";
    }
}
