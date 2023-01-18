package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1, initialValue = 100)
    private Long id;
    private String title;
    private String writer;
    private String category;
    private Integer stock;
    @OneToMany(targetEntity = TransactionDetail.class)
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    @JsonIgnore
    private List<TransactionDetail> transactionDetail;
    @Transient
    private HashMap<String, Object> transactions;

    public Book(String title, String writer, String category, Integer stock) {
        this.title = title;
        this.writer = writer;
        this.category = category;
        this.stock = stock;
    }

    // show transactionDetail list and total tranasactionDetail each book
    public HashMap<String, Object> getTransactions() {
        HashMap<String, Object> _transactions = new HashMap<>();
        _transactions.put("total", transactionDetail.size());
        _transactions.put("data", transactionDetail);
        return _transactions;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", writer='" + writer + '\'' +
            ", category='" + category + '\'' +
            ", stock=" + stock +
            '}';
    }

}
