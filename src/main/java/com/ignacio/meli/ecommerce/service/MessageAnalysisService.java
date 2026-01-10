package com.ignacio.meli.ecommerce.service;

import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;
import org.springframework.stereotype.Service;

@Service
public class MessageAnalysisService {

    public void analyze(Message message) {

        String text = message.getMessageText().toLowerCase();

        if (isSimpleQuestion(text)) {
            message.setAiResponse(buildAutoResponse(message));
            message.setStatus(MessageStatus.AUTO_REPLIED);
        } else {
            message.setStatus(MessageStatus.DERIVED_WHATSAPP);
        }
    }

    private boolean isSimpleQuestion(String text) {
        return text.contains("precio")
            || text.contains("stock")
            || text.contains("envio")
            || text.contains("envío")
            || text.contains("disponible");
    }

    private String buildAutoResponse(Message message) {
        var product = message.getProduct();

        return """
        Hola 👋
        El producto "%s" tiene un precio de $%s y actualmente hay %d unidades disponibles.
        Envíos a todo el país 🚚
        """.formatted(
                product.getTitle(),
                product.getPrice(),
                product.getStock()
        );
    }
}
