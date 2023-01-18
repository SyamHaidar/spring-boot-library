package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionNumber;
    private Long memberId;
    private LocalDate loanDate;
    private LocalDate estimatedDate;
    private LocalDate returnDate;
    private String totalCharge;
    @Transient
    private List<Long> bookId;
    @OneToMany(targetEntity = TransactionDetail.class)
    @JoinColumn(name = "transactionId", referencedColumnName = "id")
    private List<TransactionDetail> transactionDetail;

    public Transaction(String transactionNumber, Long memberId, LocalDate loanDate, LocalDate estimatedDate, LocalDate returnDate, String totalCharge) {
        this.transactionNumber = transactionNumber;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.estimatedDate = estimatedDate;
        this.returnDate = returnDate;
        this.totalCharge = totalCharge;
    }

    @JsonIgnore
    public List<Long> getBookId() {
        return bookId;
    }

    @JsonSetter
    public void setBookId(List<Long> bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionNumber='" + transactionNumber + '\'' +
                ", memberId=" + memberId +
                ", loanDate='" + loanDate + '\'' +
                ", estimatedDate='" + estimatedDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", totalCharge='" + totalCharge + '\'' +
                '}';
    }
}
