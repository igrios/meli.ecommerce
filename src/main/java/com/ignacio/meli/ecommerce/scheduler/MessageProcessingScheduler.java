package com.ignacio.meli.ecommerce.scheduler;

import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;
import com.ignacio.meli.ecommerce.repository.MessageRepository;
import com.ignacio.meli.ecommerce.service.MessageDecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageProcessingScheduler {

    private final MessageRepository messageRepository;
    private final MessageDecisionService decisionService;

    @Scheduled(fixedDelay = 30_000) // cada 30 segundos
    @Transactional
    public void processPendingMessages() {

        List<Message> pending = messageRepository.findByStatusIn(
                List.of(MessageStatus.RECEIVED, MessageStatus.PENDING_REVIEW)
        );

        if (pending.isEmpty()) {
            return;
        }

        System.out.println("📨 Procesando " + pending.size() + " mensajes pendientes");

        for (Message message : pending) {
            decisionService.process(message);
            messageRepository.save(message);
        }
    }
}
