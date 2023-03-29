package com.example.livecode_ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Setter @Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "grand_total")
    private Integer grandTotal;
}
