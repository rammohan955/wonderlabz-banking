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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private String accountNumber;
    // TODO create static class for account types and have mapping
    private String accountType;
    private BigDecimal currentBalance;
    private String userName;
    private transient List<Transaction> transactionHistory;

}