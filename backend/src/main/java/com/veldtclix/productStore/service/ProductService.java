package com.veldtclix.productStore.service;

import com.veldtclix.productStore.dto.ProductRequest;
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
            product.setNumber(request.getNumber());
            product.setTimeStamp(Instant.now().toString());
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save Expense: "+ e.getMessage());
        }
    }
}
