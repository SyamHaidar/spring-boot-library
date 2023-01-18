package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transactionId;
    private Long bookId;
    private String charge;

    public TransactionDetail(Long transactionId, Long bookId, String charge) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id='" + id + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", bookId=" + bookId +
                ", charge='" + charge + '\'' +
                '}';
    }
}
