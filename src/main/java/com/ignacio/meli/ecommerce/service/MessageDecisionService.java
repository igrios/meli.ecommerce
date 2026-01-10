package com.ignacio.meli.ecommerce.service;

import org.springframework.stereotype.Service;
import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;

@Service
public class MessageDecisionService {

    public void process(Message message) {
        String text = message.getMessageText().toLowerCase();

        if (isComplex(text)) {
            message.setStatus(MessageStatus.DERIVED_WHATSAPP);
            message.setAiResponse(null);
        } else {
            message.setStatus(MessageStatus.AUTO_REPLIED);
            message.setAiResponse(generateAutoReply(message));
        }
    }

    private boolean isComplex(String text) {
        return text.contains("factura")
            || text.contains("horario")
            || text.contains("antes de")
            || text.contains("mañana")
            || text.contains("urgente")
            || text.contains("hablar")
            || text.contains("humano")
            || text.contains("personalizado");
    }

    private String generateAutoReply(Message message) {
        return "Hola 👋 El precio del producto es $" 
                + message.getProduct().getPrice()
                + ". Si querés comprarlo, decime y te ayudo.";
    }
}
