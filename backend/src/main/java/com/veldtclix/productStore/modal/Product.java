package com.veldtclix.productStore.model;

import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Product")
public class Product {
    @Id
    private String id;

    private String name;
    private double number;
    private String image;
    private String timeStamp;
}
