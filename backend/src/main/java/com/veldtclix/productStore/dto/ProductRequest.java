package com.veldtclix.productStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Number is required")
    private double number;

    @NotBlank(message = "Image is required")
    private String image;
}
