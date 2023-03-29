package com.example.livecode_ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class DailyReportRequest {
    private Date date;
}
