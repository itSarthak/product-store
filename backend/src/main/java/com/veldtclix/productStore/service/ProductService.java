package com.veldtclix.productStore.service;
import com.veldtclix.productStore.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(String id);

    ProductDto updateProduct(String id, ProductDto productDto);

    void deleteProduct(String id);
}
