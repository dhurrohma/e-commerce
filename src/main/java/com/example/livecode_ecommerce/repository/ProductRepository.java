package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findByNameContains(String name);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :startPrice AND :endPrice")
    List<Product> findByPrice(Integer startPrice, Integer endPrice);

    @Query("SELECT p FROM Product p WHERE p.category.id = :category_id")
    public List<Product> findByCategoryId(Long category_id);

    List<Product> findAll(Specification specification);
}
