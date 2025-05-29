package com.veldtclix.productStore.repository;

import com.veldtclix.productStore.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
