package com.ignacio.meli.ecommerce.repository;

import com.ignacio.meli.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Buscar producto por item de Mercado Libre
    Optional<Product> findByMeliItemId(String meliItemId);

    // Saber si ya existe un producto publicado en ML
    boolean existsByMeliItemId(String meliItemId);
}