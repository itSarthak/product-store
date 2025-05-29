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

    /****
     * Constructs a ProductController with the specified ProductService.
     *
     * @param productService the service used to handle product-related operations
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /****
     * Retrieves a list of all products.
     *
     * @return a ResponseEntity containing a list of ProductDto objects representing all products
     */
    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Creates a new product using the provided product data.
     *
     * @param request the product details to create, validated before processing
     * @return the created product as a ProductDto wrapped in a ResponseEntity
     */
    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto request) {
        log.info("HTTP POST request received");
        ProductDto savedProduct = productService.createProduct(request);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * Updates an existing product with the specified ID using the provided product data.
     *
     * @param id the unique identifier of the product to update
     * @param productDto the product data to update
     * @return the updated product details
     */
    @PutMapping(path = "/product/{id")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Deletes a product by its ID and returns a confirmation message.
     *
     * @param id the unique identifier of the product to delete
     * @return a response entity containing a success message
     */
    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable String id) {
        log.info("HTTP DELETE request received");
        productService.deleteProduct(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
