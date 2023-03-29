package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.dto.TransactionRequest;
import com.example.livecode_ecommerce.model.entity.Product;
import com.example.livecode_ecommerce.model.entity.Transaction;
import com.example.livecode_ecommerce.model.response.PagingResponse;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.TransactionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        Transaction result = transactionService.create(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>("Success create transaction", result));
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam Integer page, @RequestParam String sortBy, @RequestParam String direction) {
        Page<Transaction> result = transactionService.findAll(page, 10, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PagingResponse<>("Success get all transaction", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws Exception {
        Optional<Transaction> transaction = transactionService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get transaction with id ", transaction));
    }

    @GetMapping("/date")
    public ResponseEntity getByDate(@RequestParam String date) throws Exception {
        List<Transaction> transactions = transactionService.findByDate(date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get transaction with date "+date, transactions));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Transaction transaction, @PathVariable Long id) {
        transactionService.update(transaction, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success update transaction", transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        transactionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success delete transaction", id));
    }
}
