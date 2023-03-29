package com.example.livecode_ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter @Getter
@ToString
public class MonthlyReport {
    private Date startDate;

    private Date endDate;
}
