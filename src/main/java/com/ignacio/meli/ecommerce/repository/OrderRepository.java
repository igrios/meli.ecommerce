package com.ignacio.meli.ecommerce.repository;

import com.ignacio.meli.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByMeliOrderId(String meliOrderId);
}
