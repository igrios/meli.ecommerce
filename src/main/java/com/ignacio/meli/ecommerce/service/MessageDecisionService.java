package com.ignacio.meli.ecommerce.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;

@Service
@RequiredArgsConstructor
public class MessageDecisionService {

    private final MessageReplyService replyService;
    private final WhatsappNotificationService whatsappService;

    public void process(Message message) {

        String text = message.getMessageText().toLowerCase();

        if (isComplex(text)) {
            message.setStatus(MessageStatus.DERIVED_WHATSAPP);
            message.setAiResponse(null);
            whatsappService.notifyHuman(message);
        } else {
            message.setStatus(MessageStatus.AUTO_REPLIED);
            message.setAiResponse(replyService.generateReply(message));
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
}
