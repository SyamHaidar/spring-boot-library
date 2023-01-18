package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Object> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable("id") String id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody Transaction transaction) {
        return transactionService.add(transaction);
    }

    @PutMapping(path = "{id}/update")
    public ResponseEntity<Object> update(@PathVariable("id") String id) {
        return transactionService.update(id);
    }

    @DeleteMapping(path = "{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        return transactionService.delete(id);
    }
}
