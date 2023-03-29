package com.example.livecode_ecommerce.model.dto;

import com.example.livecode_ecommerce.model.entity.ProductCategory;
import com.example.livecode_ecommerce.model.entity.Transaction;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class ProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private Integer price;
    private ProductCategory category;
}
