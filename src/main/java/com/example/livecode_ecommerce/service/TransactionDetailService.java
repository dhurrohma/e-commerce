package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.entity.Transaction;
import com.example.livecode_ecommerce.model.entity.TransactionDetail;
import com.example.livecode_ecommerce.repository.TransactionDetailRepository;
import com.example.livecode_ecommerce.repository.TransactionRepository;
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
public class TransactionDetailService {
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDetail create(TransactionDetail transactionDetail) {
        Integer subTotal = transactionDetail.getQty() * transactionDetail.getProduct().getPrice();
        transactionDetail.setSubTotal(subTotal);

        Integer grandTotal = transactionDetailRepository.grandTotal(transactionDetail.getTransaction().getId());
        Optional<Transaction> transaction = transactionRepository.findById(transactionDetail.getTransaction().getId());
        transaction.get().setGrandTotal(transaction.get().getGrandTotal() + subTotal);

        return transactionDetailRepository.save(transactionDetail);
    }

    public Page<TransactionDetail> findAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<TransactionDetail> result = transactionDetailRepository.findAll(pageable);
        return result;
    }

    public Optional<TransactionDetail> findById(Long id) throws Exception {
        Optional<TransactionDetail> transactionDetail = transactionDetailRepository.findById(id);
        if (transactionDetail.isEmpty()){
            throw new Exception("Transaction detail not found");
        }
        return transactionDetail;
    }

    public List<TransactionDetail> findByTransactionId(Long transaction_id) throws Exception {
        List<TransactionDetail> transactionDetails = transactionDetailRepository.findByTransactionId(transaction_id);
        if (transactionDetails.isEmpty()){
            throw new Exception("There are no transaction detain in that category");
        }
        return transactionDetails;
    }

    public List<TransactionDetail> findByProductId(Long product_id) throws Exception {
        List<TransactionDetail> transactionDetails = transactionDetailRepository.findByProductId(product_id);
        if (transactionDetails.isEmpty()){
            throw new Exception("There are no transaction detain in that category");
        }
        return transactionDetails;
    }

    public void update(TransactionDetail transactionDetail, Long id){
        try {
            Optional<TransactionDetail> existingTransDetail = findById(id);
            transactionDetail.setId(existingTransDetail.get().getId());
            transactionDetailRepository.save(transactionDetail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        try {
            Optional<TransactionDetail> existingTransaction = findById(id);
            transactionDetailRepository.delete(existingTransaction.get());
        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }

    public Integer subTotal(Long id){
        return transactionDetailRepository.subTotal(id);
    }
}
