package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(generator = "member_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1, initialValue = 1000)
    private Long id;
    private String fullName;
    private String address;
    private String gender;
    private LocalDate dob;
    private String mobile;
    @OneToMany(targetEntity = Transaction.class)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private List<Transaction> transactions;

    public Member(String fullName, String address, String gender, LocalDate dob, String mobile) {
        this.fullName = fullName;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

}
