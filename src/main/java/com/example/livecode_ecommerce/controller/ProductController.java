package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.dto.ProductRequest;
import com.example.livecode_ecommerce.model.entity.Product;
import com.example.livecode_ecommerce.model.response.PagingResponse;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.ProductService;
import com.example.livecode_ecommerce.utils.constants.FindOperator;
import com.example.livecode_ecommerce.utils.specification.SearchCriteria;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product result = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Success create product", result));
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam String sortBy, @RequestParam String direction) {
        Page<Product> result = productService.findAll(page, 10, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PagingResponse<>("Success get all product", result));
    }

    @GetMapping(params = {"key", "value", "operator"})
    public ResponseEntity getBy(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("operator") String operator) throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria(key, FindOperator.valueOf(operator), value);
        List<Product> products = productService.getBy(searchCriteria);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product by", products));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Product> product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<Product>>("Success get product with id "+id, product));
    }

    @GetMapping("/name")
    public ResponseEntity getByName(@RequestParam String name) throws Exception {
        List<Product> products = productService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product with name "+name, products));
    }

    @GetMapping("/category_id")
    public ResponseEntity getByCategoryId(@RequestParam Long categoryId) throws Exception {
        List<Product> products = productService.findByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product with category id "+categoryId, products));
    }

    @GetMapping("/price")
    public ResponseEntity getByPrice(@RequestParam Integer startPrice, @RequestParam Integer endPrice) throws Exception {
        List<Product> products = productService.findByPrice(startPrice, endPrice);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product", products));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Product product, @PathVariable Long id) {
        productService.update(product, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success update product", product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success delete product with id "+id, id));
    }
}
