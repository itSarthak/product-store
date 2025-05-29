package com.veldtclix.productStore.controller;

import com.veldtclix.productStore.dto.ProductRequest;
import com.veldtclix.productStore.model.Product;
import com.veldtclix.productStore.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductRequest request) {
        log.info("HTTP POST request received");
        Product savedProduct = productService.saveProduct(request);
        return ResponseEntity.ok(savedProduct);
    }
}
