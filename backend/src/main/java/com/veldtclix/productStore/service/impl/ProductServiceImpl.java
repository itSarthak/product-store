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
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setTimeStamp(Instant.now().toString());
        Product saved = productRepository.save(product);

        ProductDto responseDto = new ProductDto();
        BeanUtils.copyProperties(saved, responseDto);
        return responseDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return convertToDto(product);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        existing.setName(productDto.getName());
        existing.setPrice(productDto.getPrice());
        existing.setImage(productDto.getImage());
        existing.setTimeStamp(Instant.now().toString());

        Product updated = productRepository.save(existing);
        return convertToDto(updated);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}