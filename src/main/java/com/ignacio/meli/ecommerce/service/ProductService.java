package com.ignacio.meli.ecommerce.service;

import com.ignacio.meli.ecommerce.domain.Product;
import com.ignacio.meli.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Crear o actualizar producto
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Obtener todos los productos
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Buscar por ID
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Buscar por Meli Item ID
    public Optional<Product> findByMeliItemId(String meliItemId) {
        return productRepository.findByMeliItemId(meliItemId);
    }

    // Eliminar producto
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    // Verificar si existe en Mercado Libre
    public boolean existsByMeliItemId(String meliItemId) {
        return productRepository.existsByMeliItemId(meliItemId);
    }
}
