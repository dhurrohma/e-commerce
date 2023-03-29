package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.TransactionDetail;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>, PagingAndSortingRepository<TransactionDetail, Long> {
    @Query("SELECT td FROM TransactionDetail td WHERE td.transaction.id = :transaction_id")
    public List<TransactionDetail> findByTransactionId(Long transaction_id);

    @Query("SELECT td FROM TransactionDetail td WHERE td.product.id = :product_id")
    public List<TransactionDetail> findByProductId(Long product_id);

    @Query("SELECT SUM(qty * product.price) FROM TransactionDetail WHERE id = :id")
    public Integer subTotal(Long id);

    @Query("SELECT SUM(td.subTotal) FROM TransactionDetail td WHERE td.transaction.id = :transaction_id")
    public Integer grandTotal(Long transaction_id);

    List<TransactionDetail> findByTransactionDate(Date date);
}
