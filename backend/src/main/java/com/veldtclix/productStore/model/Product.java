package com.veldtclix.productStore.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Product")
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private String image;
    private String timeStamp;
}
