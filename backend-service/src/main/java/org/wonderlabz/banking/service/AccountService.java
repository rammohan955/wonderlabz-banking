package org.wonderlabz.banking.service;

import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.request.CreateAccountRequest;

import java.util.List;

public interface AccountService {
    Account createAccount(CreateAccountRequest createAccountRequest);

    Account getAccount(String accountNumber);

    boolean updateBalance(Account account);

    List<Account> getAllAccounts();
}
