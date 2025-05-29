package com.veldtclix.productStore.modal;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Product")
public class Product {
    private String name;
    private double number;
    private String image;
    private String timeStamp;
}
