package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.entity.ProductCategory;
import com.example.livecode_ecommerce.repository.ProductCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory create(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public Page<ProductCategory> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<ProductCategory> result = productCategoryRepository.findAll(pageable);
        return result;
    }

    public Optional<ProductCategory> findById(Long id) throws Exception {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(id);
        if(productCategory.isEmpty()){
            throw new Exception("Product category not found");
        }
        return productCategory;
    }

    public List<ProductCategory> findByCategory(String category) throws Exception {
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryContains(category);
        if (productCategories.isEmpty()){
            throw new Exception("Product category not found");
        }
        return productCategories;
    }

    public void update(ProductCategory productCategory, Long id){
        try {
            Optional<ProductCategory> existingCategory = findById(id);
            productCategory.setId(existingCategory.get().getId());
            productCategoryRepository.save(productCategory);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Optional<ProductCategory> existingCategory = findById(id);
            productCategoryRepository.delete(existingCategory.get());
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }

}
