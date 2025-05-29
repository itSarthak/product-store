package com.veldtclix.productStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private double price;

    @NotBlank(message = "Image is required")
    private String image;
}
