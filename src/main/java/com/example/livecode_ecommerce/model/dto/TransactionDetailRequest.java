package com.example.livecode_ecommerce.model.dto;

import com.example.livecode_ecommerce.model.entity.Product;
import com.example.livecode_ecommerce.model.entity.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TransactionDetailRequest {
    private Transaction transaction;
    private Product product;
    private Integer qty;
}
