package com.example.livecode_ecommerce.repository;

import com.example.livecode_ecommerce.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findByDate(Date date);

    @Query("SELECT SUM(t.grandTotal) FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
    Integer monthlyReport(Date startDate, Date endDate);
}
