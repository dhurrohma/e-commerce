package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.entity.Transaction;
import com.example.livecode_ecommerce.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction create(Transaction transaction) {
        transaction.setGrandTotal(0);
        return transactionRepository.save(transaction);
    }

    public Page<Transaction> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Transaction> result = transactionRepository.findAll(pageable);
        return result;
    }

    public Optional<Transaction> findById(Long id) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()){
            throw new Exception("Product not found");
        }
        return transaction;
    }

    public List<Transaction> findByDate(String date) throws Exception {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        List<Transaction> transactions = transactionRepository.findByDate(date1);
        if (transactions.isEmpty()){
            throw new Exception("There are no transactions on this date");
        }
        return transactions;
    }

    public void update(Transaction transaction, Long id){
        try {
            Optional<Transaction> existingTransaction = findById(id);
            transaction.setId(existingTransaction.get().getId());
            transactionRepository.save(transaction);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Optional<Transaction> existingTransaction = findById(id);
            transactionRepository.delete(existingTransaction.get());
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}
