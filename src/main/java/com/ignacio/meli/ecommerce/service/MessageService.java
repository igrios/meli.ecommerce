package com.ignacio.meli.ecommerce.service;

//import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;
import com.ignacio.meli.ecommerce.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageDecisionService decisionService;
    
   
    
    public Message receiveMessage(Message message) {

    	System.out.println("🔥 MessageService REAL ejecutado");
    	
        if (message.getMeliMessageId() != null &&
            messageRepository.findByMeliMessageId(message.getMeliMessageId()).isPresent()) {
            return null;
        }

        message.setStatus(MessageStatus.RECEIVED);

        decisionService.process(message);

        return messageRepository.save(message);
    }
}
