package com.veldtclix.productStore.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.veldtclix.productStore.dto.ProductDto;
import com.veldtclix.productStore.exception.ResourceNotFoundException;
import com.veldtclix.productStore.model.Product;
import com.veldtclix.productStore.repository.ProductRepository;
import com.veldtclix.productStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            product.setTimeStamp(Instant.now().toString());
            Product saved = productRepository.save(product);

            ProductDto responseDto = new ProductDto();
            BeanUtils.copyProperties(saved, responseDto);
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a new Product "+ e.getMessage());
        }

    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            return productRepository.findAll()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to create a list all the Products "+ e.getMessage());
        }
    }

    @Override
    public ProductDto getProductById(String id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
            return convertToDto(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get a product by its ID "+ e.getMessage());
        }
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        try {
            Product existing = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

            existing.setName(productDto.getName());
            existing.setPrice(productDto.getPrice());
            existing.setImage(productDto.getImage());
            existing.setTimeStamp(Instant.now().toString());

            Product updated = productRepository.save(existing);
            return convertToDto(updated);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to update a Product "+ e.getMessage());
        }

    }

    @Override
    public void deleteProduct(String id) {
        try {
            Product existing = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
            productRepository.delete(existing);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to delete a Product "+ e.getMessage());
        }

    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}