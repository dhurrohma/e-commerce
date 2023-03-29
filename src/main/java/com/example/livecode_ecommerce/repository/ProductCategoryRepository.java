package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, PagingAndSortingRepository<ProductCategory, Long> {
    List<ProductCategory> findByCategoryContains(String category);
}
