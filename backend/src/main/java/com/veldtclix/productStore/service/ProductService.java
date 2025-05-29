package com.veldtclix.productStore.service;

import com.veldtclix.productStore.dto.ProductRequest;
import com.veldtclix.productStore.exception.ResourceNotFoundException;
import com.veldtclix.productStore.model.Product;
import com.veldtclix.productStore.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(ProductRequest request) {
        try {
            log.info("Request forwarded to the Service layer");
            Product product = new Product();
            product.setName(request.getName());
            product.setImage(request.getImage());
            product.setPrice(request.getPrice());
            product.setTimeStamp(Instant.now().toString());
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Product: "+ e.getMessage());
        }
    }
    public boolean deleteProduct(String id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
            productRepository.delete(product);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
