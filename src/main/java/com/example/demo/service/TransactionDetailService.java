package com.example.demo.service;

import com.example.demo.entity.TransactionDetail;
import com.example.demo.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionDetailService {

    @Autowired
    private TransactionDetailRepository tdRepository;

    public ResponseEntity<Object> getAllTransactionDetail() {
        try {
            return new ResponseEntity<>(tdRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTransactionDetail(Long transactionId) {
        try {
            Optional<TransactionDetail> data = tdRepository.findByTransactionId(transactionId);
            if (data.isPresent()) {
                TransactionDetail _td = data.get();
                return new ResponseEntity<>(_td, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
