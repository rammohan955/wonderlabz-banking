package org.wonderlabz.banking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    // TODO create static class for Transaction types and have mapping
    private String transactionType;
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal transactionAmount;
    private Date transactionDateTime;
    private String comments;

}