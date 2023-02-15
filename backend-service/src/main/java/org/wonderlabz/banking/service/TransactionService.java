package org.wonderlabz.banking.service;

import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.entity.Transaction;
import org.wonderlabz.banking.request.DepositRequest;
import org.wonderlabz.banking.request.TransferRequest;
import org.wonderlabz.banking.request.WithdrawRequest;

import java.util.List;

public interface TransactionService {
    boolean makeTransfer(Account sourceAccount, Account targetAccount, TransferRequest transferRequest);

    Transaction saveTransaction(Transaction transaction);

    boolean withdraw(Account account, WithdrawRequest withdrawRequest);

    boolean deposit(Account account, DepositRequest depositRequest);

    List<Transaction> getAllTransactions();
}
