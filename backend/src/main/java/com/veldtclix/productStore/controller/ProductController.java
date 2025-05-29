package com.veldtclix.productStore.controller;

import com.veldtclix.productStore.dto.ProductRequest;
import com.veldtclix.productStore.model.Product;
import com.veldtclix.productStore.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable String id) {
        log.info("HTTP DELETE request received");
        productService.deleteProduct(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }

}
