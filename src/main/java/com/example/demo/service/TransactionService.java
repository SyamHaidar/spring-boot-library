package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.repository.TransactionDetailRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionDetailRepository tdRepository;

    public ResponseEntity<Object> getAllTransactions() {
        try {
            return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTransaction(String id) {
        try {
            Optional<Transaction> data = transactionRepository.findByTransactionNumber(id);
            if (data.isPresent()) {
                Transaction _transaction = data.get();
                return new ResponseEntity<>(_transaction, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> add(Transaction transaction) {
        try {
            String loanDate = transaction.getLoanDate().toString();
            String TransactionNumber = Utility.dateFormat(loanDate) + "-" + transaction.getMemberId();

            // insert transaction
            Transaction _transaction = transactionRepository.save(new Transaction(
                    TransactionNumber, // custom transaction_number
                    transaction.getMemberId(),
                    transaction.getLoanDate(),
                    Utility.daysToAdd(loanDate, 7), // estimated loan is one week
                    transaction.getReturnDate(),
                    transaction.getTotalCharge()
            ));

            // insert transaction_detail
            for (Long book : transaction.getBookId()) {
                tdRepository.save(new TransactionDetail(
                        _transaction.getId(),
                        book,
                        transaction.getTotalCharge()));
            }

            return new ResponseEntity<>(_transaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> update(String id) {
        try {
            Optional<Transaction> data = transactionRepository.findByTransactionNumber(id);
            if (data.isPresent()) {
                Transaction _transaction = data.get();

                int totalBook = _transaction.getTransactionDetail().size();
                int overtime = Period.between(_transaction.getEstimatedDate(), LocalDate.now()).getDays();
                int charge = overtime * 1000;
                int totalCharge = charge * totalBook;

                // update returnDate and totalCharge
                _transaction.setReturnDate(LocalDate.now());
                _transaction.setTotalCharge(String.valueOf(totalCharge));

                // update transaction_detail charge per rows where boookId same as transaction.id
                List<TransactionDetail> items = tdRepository.findAllByTransactionId(_transaction.getId());
                for (TransactionDetail _td : items) {
                    _td.setCharge(String.valueOf(charge));
                    tdRepository.save(_td);
                }

                return new ResponseEntity<>(transactionRepository.save(_transaction), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        try {
            boolean _transaction = transactionRepository.existsById(id);
            if (_transaction) {
                transactionRepository.deleteById(id);
                return new ResponseEntity<>("Transaction deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
