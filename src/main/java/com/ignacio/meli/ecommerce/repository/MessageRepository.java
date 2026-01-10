package com.ignacio.meli.ecommerce.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignacio.meli.ecommerce.domain.Message;
import com.ignacio.meli.ecommerce.domain.MessageStatus;


public interface MessageRepository extends JpaRepository<Message,Long>{
	
	  Optional<Message> findByMeliMessageId(String meliMessageId);

	    List<Message> findByStatus(MessageStatus status);

}
