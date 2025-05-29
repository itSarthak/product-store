package com.veldtclix.productStore.controller;

import com.veldtclix.productStore.dto.ProductDto;
import com.veldtclix.productStore.model.Product;
import com.veldtclix.productStore.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto request) {
        log.info("HTTP POST request received");
        ProductDto savedProduct = productService.createProduct(request);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping(path = "/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id,@Valid @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
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
