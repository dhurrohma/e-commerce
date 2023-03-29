package com.example.livecode_ecommerce.service;

import com.example.livecode_ecommerce.model.entity.TransactionDetail;
import com.example.livecode_ecommerce.repository.TransactionDetailRepository;
import com.example.livecode_ecommerce.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    public List<TransactionDetail> dailyReport(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return transactionDetailRepository.findByTransactionDate(date1);
    }

    public Integer monthlyReport(String startDate, String endDate) throws ParseException {
        Date startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        return transactionRepository.monthlyReport(startDate1, endDate1);
    }

}
