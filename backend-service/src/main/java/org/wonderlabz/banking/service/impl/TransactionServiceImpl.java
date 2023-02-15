package org.wonderlabz.banking.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.entity.Transaction;
import org.wonderlabz.banking.repository.AccountRepository;
import org.wonderlabz.banking.repository.TransactionRepository;
import org.wonderlabz.banking.request.DepositRequest;
import org.wonderlabz.banking.request.TransferRequest;
import org.wonderlabz.banking.request.WithdrawRequest;
import org.wonderlabz.banking.service.TransactionService;
import org.wonderlabz.banking.types.TransactionType;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    @Transactional
    public boolean makeTransfer(Account sourceAccount, Account targetAccount, TransferRequest transferRequest) {
        sourceAccount.setCurrentBalance(sourceAccount.getCurrentBalance().subtract(new BigDecimal(transferRequest.getAmount())));
        targetAccount.setCurrentBalance(targetAccount.getCurrentBalance().add(new BigDecimal(transferRequest.getAmount())));
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER.toString());
        transaction.setTransactionAmount(new BigDecimal(transferRequest.getAmount()));
        transaction.setSourceAccountId(sourceAccount.getAccountId());
        transaction.setTargetAccountId(targetAccount.getAccountId());
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        transactionRepository.save(transaction);
        return false;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public boolean withdraw(Account account, WithdrawRequest withdrawRequest) {
        account.setCurrentBalance(account.getCurrentBalance().subtract(new BigDecimal(withdrawRequest.getAmount())));
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER.toString());
        transaction.setTransactionAmount(new BigDecimal(withdrawRequest.getAmount()));
        transaction.setSourceAccountId(account.getAccountId());
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return true;
    }

    @Override
    public boolean deposit(Account account, DepositRequest depositRequest) {
        account.setCurrentBalance(account.getCurrentBalance().add(new BigDecimal(depositRequest.getAmount())));
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSFER.toString());
        transaction.setTransactionAmount(new BigDecimal(depositRequest.getAmount()));
        transaction.setTargetAccountId(account.getAccountId());
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return true;
    }
}
