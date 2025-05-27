package com.veldtclix.productStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductStoreApplication {

	public static void main(String[] args) {
		System.out.println("MongoDB URI: " + System.getenv("DATABASE_URL"));
		SpringApplication.run(ProductStoreApplication.class, args);
	}

}
