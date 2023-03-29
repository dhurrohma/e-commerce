package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.dto.ProductCategoryRequest;
import com.example.livecode_ecommerce.model.entity.ProductCategory;
import com.example.livecode_ecommerce.model.response.PagingResponse;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.ProductCategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductCategoryRequest productCategoryRequest) {
        ProductCategory productCategory = modelMapper.map(productCategoryRequest, ProductCategory.class);
        ProductCategory result = productCategoryService.create(productCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Success create product category", result));
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam String sortBy, @RequestParam String direction) {
        Page<ProductCategory> result = productCategoryService.findAll(page, 10, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PagingResponse<>("Success get all product category", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<ProductCategory> productCategory = productCategoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<ProductCategory>>("Success get product category with id "+id, productCategory));
    }

    @GetMapping("/category")
    public ResponseEntity findByCategory(@RequestParam String category) throws Exception {
        List<ProductCategory> productCategories = productCategoryService.findByCategory(category);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product with category "+category, productCategories));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody ProductCategory productCategory, @PathVariable Long id) {
        productCategoryService.update(productCategory, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<ProductCategory>("Success update product category", productCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        productCategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success delete product category with id "+id, id));
    }
}
