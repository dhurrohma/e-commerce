package com.example.livecode_ecommerce.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCategoryRequest {
    @NotBlank(message = "Category is required")
    private String category;
}
