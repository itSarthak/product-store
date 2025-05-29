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


    /****
     * Creates a new product using the provided data transfer object.
     *
     * Copies properties from the given ProductDto to a new Product entity, sets the current timestamp, saves it to the repository, and returns the saved product as a ProductDto.
     *
     * @param productDto the data transfer object containing product details
     * @return the created product as a ProductDto
     * @throws RuntimeException if product creation fails
     */
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

    /**
     * Retrieves all products from the repository and returns them as a list of DTOs.
     *
     * @return a list of all products represented as ProductDto objects
     */
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

    /**
     * Retrieves a product by its unique identifier and returns its data as a ProductDto.
     *
     * @param id the unique identifier of the product to retrieve
     * @return the ProductDto representing the found product
     * @throws ResourceNotFoundException if no product exists with the given ID
     * @throws RuntimeException if an unexpected error occurs during retrieval
     */
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

    /**
     * Updates an existing product with new details provided in the DTO.
     *
     * @param id the unique identifier of the product to update
     * @param productDto the data transfer object containing updated product information
     * @return the updated product as a DTO
     * @throws RuntimeException if the product is not found or the update fails
     */
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

    /****
     * Deletes a product with the specified ID.
     *
     * If the product does not exist, a ResourceNotFoundException is thrown.
     * Wraps any runtime exceptions in a RuntimeException with a descriptive message.
     *
     * @param id the unique identifier of the product to delete
     */
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

    /**
     * Converts a Product entity to a ProductDto by copying its properties.
     *
     * @param product the Product entity to convert
     * @return a ProductDto containing the copied properties from the given Product
     */
    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}