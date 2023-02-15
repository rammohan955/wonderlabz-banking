package org.wonderlabz.banking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.request.CreateAccountRequest;
import org.wonderlabz.banking.service.AccountService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);
    public static final String CREATE_ACCOUNT_FAILED =
            "Error happened during creating new account";
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(
            @Valid @RequestBody CreateAccountRequest createAccountRequest) {
        LOGGER.debug("Triggered AccountRestController.createAccount");
        // TODO Validate existing Account number and User details
        Account account = accountService.createAccount(createAccountRequest);
        if (account == null) {
            return new ResponseEntity<>(CREATE_ACCOUNT_FAILED, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    @GetMapping("/view")
//    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<?> getAccounts() {
        LOGGER.debug("Triggered AccountRestController.getAccounts");
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}