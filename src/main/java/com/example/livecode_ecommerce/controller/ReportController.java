package com.example.livecode_ecommerce.controller;

import com.example.livecode_ecommerce.model.entity.TransactionDetail;
import com.example.livecode_ecommerce.model.response.PagingResponse;
import com.example.livecode_ecommerce.model.response.SuccessResponse;
import com.example.livecode_ecommerce.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity dailyReport(@RequestParam String date) throws ParseException {
        List<TransactionDetail> result = reportService.dailyReport(date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<>("Success get daily report", result));
    }

    @GetMapping("/monthly")
    public ResponseEntity monthlyReport(@RequestParam String startDate, String endDate) throws ParseException {
        Integer result = reportService.monthlyReport(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<Integer>("Success get daily report", result));
    }
}
