package com.veldtclix.productStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Number is required")
    private double number;

    @NotBlank(message = "Image is required")
    private String image;
}
