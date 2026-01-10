package com.ignacio.meli.ecommerce.repository;


import com.ignacio.meli.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>{
	
	//Optional<Order> findByOrderId(String meliOrderId);
	
	
	

}
