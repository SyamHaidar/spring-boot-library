package com.example.demo.controller;

import com.example.demo.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction-detail")
public class TransactionDetailController {

    @Autowired
    private TransactionDetailService tdService;

    @GetMapping
    public ResponseEntity<Object> getAllTransactionDetail() {
        return tdService.getAllTransactionDetail();
    }

    @GetMapping(path = "{transactionId}")
    public ResponseEntity<Object> getTransactionDetail(@PathVariable("transactionId") Long transactionId) {
        return tdService.getTransactionDetail(transactionId);
    }

}
