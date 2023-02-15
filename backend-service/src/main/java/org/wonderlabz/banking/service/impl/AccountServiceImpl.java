package org.wonderlabz.banking.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wonderlabz.banking.controller.AccountRestController;
import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.repository.AccountRepository;
import org.wonderlabz.banking.request.CreateAccountRequest;
import org.wonderlabz.banking.request.TransferRequest;
import org.wonderlabz.banking.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {
        LOGGER.info("Create Account Request received");
        Account account = new Account();
        account.setAccountType(createAccountRequest.getAccountType());
        account.setAccountNumber(createAccountRequest.getAccountNumber());
        account.setUserName(createAccountRequest.getUserName());
        account.setCurrentBalance(new BigDecimal(createAccountRequest.getOpeningBalance()));
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    @Override
    public Account getAccount(String accountNumber) {
        LOGGER.info("Get Account details received");
        return accountRepository.findByAccountNumberEquals(accountNumber);
    }

    @Override
    public boolean updateBalance(Account account) {
        return false;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
