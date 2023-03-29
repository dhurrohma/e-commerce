package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.dto.TransactionDetailRequest;
import com.example.livecode_ecommerce.model.entity.TransactionDetail;
import com.example.livecode_ecommerce.model.response.PagingResponse;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.TransactionDetailService;
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
@RequestMapping("/transaction_detail")
public class TransactionDetailController {
    @Autowired
    private TransactionDetailService transactionDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TransactionDetailRequest transactionDetailRequest) {
        TransactionDetail transactionDetail = modelMapper.map(transactionDetailRequest, TransactionDetail.class);
        TransactionDetail result = transactionDetailService.create(transactionDetail);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Success create transaction detail", result));
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam String sortBy, @RequestParam String direction) {
        Page<TransactionDetail> result = transactionDetailService.findAll(page, 10, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PagingResponse<>("Success get all transaction detail", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<TransactionDetail> transactionDetail = transactionDetailService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Optional<TransactionDetail>>("Success get product with id "+id, transactionDetail));
    }

    @GetMapping("/transaction_id")
    public ResponseEntity getByTransactionId(@RequestParam Long transactionId) throws Exception {
        List<TransactionDetail> transactionDetails = transactionDetailService.findByTransactionId(transactionId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product with category id "+transactionId, transactionDetails));
    }

    @GetMapping("/product_id")
    public ResponseEntity getByProductId(@RequestParam Long productId) throws Exception {
        List<TransactionDetail> transactionDetails = transactionDetailService.findByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get product with category id "+productId, transactionDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TransactionDetail transactionDetail, @PathVariable Long id) {
        transactionDetailService.update(transactionDetail, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success update product", transactionDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        transactionDetailService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success delete product with id "+id, id));
    }
}
