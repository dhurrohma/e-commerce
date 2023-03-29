package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.entity.Product;
import com.example.livecode_ecommerce.repository.ProductRepository;
import com.example.livecode_ecommerce.utils.specification.SearchCriteria;
import com.example.livecode_ecommerce.utils.specification.Spec;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Product> result = productRepository.findAll(pageable);
        return result;
    }

    public List<Product> getBy(SearchCriteria searchCriteria) {
        Specification specification = new Spec<Product>().findBy(searchCriteria);
        List<Product> products = productRepository.findAll(specification);

        return products;
    }

    public Optional<Product> findById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new Exception("Product not found");
        }
        return product;
    }

    public List<Product> findByName(String name) throws Exception {
        List<Product> products = productRepository.findByNameContains(name);
        if (products.isEmpty()){
            throw new Exception("Product not found");
        }
        return products;
    }

    public List<Product> findByPrice(Integer startPrice, Integer endPrice) throws Exception {
        List<Product> products = productRepository.findByPrice(startPrice, endPrice);
        if (products.isEmpty()){
            throw new Exception("There are no product in that price range");
        }
        return products;
    }

    public List<Product> findByCategoryId(Long category_id) throws Exception {
        List<Product> products = productRepository.findByCategoryId(category_id);
        if (products.isEmpty()){
            throw new Exception("There are no product in that category");
        }
        return products;
    }

    public void update(Product product, Long id) {
        try {
            Optional<Product> existingProduct = findById(id);
            product.setId(existingProduct.get().getId());
            productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Optional<Product> existingProduct = findById(id);
            productRepository.delete(existingProduct.get());
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
