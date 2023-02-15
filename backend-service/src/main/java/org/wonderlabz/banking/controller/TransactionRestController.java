package org.wonderlabz.banking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.request.DepositRequest;
import org.wonderlabz.banking.request.TransferRequest;
import org.wonderlabz.banking.request.WithdrawRequest;
import org.wonderlabz.banking.service.AccountService;
import org.wonderlabz.banking.service.TransactionService;
import org.wonderlabz.banking.validators.ValidatorService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    // TODO move all error messages to application properties and localize them.
    public static final String SUCCESS =
            "Transaction completed successfully";
    public static final String FAILED =
            "Transaction failed.";
    public static final String INVALID_INPUT =
            "Invalid Account details";
    public static final String INSUFFICIENT_ACCOUNT_BALANCE =
            "Your account does not have sufficient balance";
    public static final String INVALID_TRANSACTION =
            "Transaction has been denied due to invalid details. Please try again.";
    private static final String NO_ACCOUNT_FOUND = "Transaction Account is invalid";

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> makeTransfer(
            @Valid @RequestBody TransferRequest transferRequest) {
        if (ValidatorService.isTransferRequestValid(transferRequest)) {
            // TODO implement locking mechanism
            Account sourceAccount = accountService.getAccount(transferRequest.getSourceAccountNumber());
            Account targetAccount = accountService.getAccount(transferRequest.getTargetAccountNumber());
            if (sourceAccount == null || targetAccount == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                if (ValidatorService.isAmountAvailable(sourceAccount, transferRequest)) {
                    boolean isComplete = transactionService.makeTransfer(sourceAccount, targetAccount, transferRequest);
                    if(isComplete) {
                        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(FAILED, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(INSUFFICIENT_ACCOUNT_BALANCE, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/withdraw",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> withdraw(
            @Valid @RequestBody WithdrawRequest withdrawRequest) {
        LOGGER.debug("Triggered TransactionRestController.withdraw");
        if (ValidatorService.isWithdrawRequestValid(withdrawRequest)) {
            // TODO implement lock mechanism before checking balance.
            Account account = accountService.getAccount(withdrawRequest.getSourceAccountNumber());
            if (account == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                if (ValidatorService.isAmountAvailable(account, new BigDecimal(withdrawRequest.getAmount()))) {
                    //account.setCurrentBalance(account.getCurrentBalance().subtract(withdrawRequest.getAmount()));
                    //boolean isComplete = accountService.updateBalance(account);
                    boolean isComplete = transactionService.withdraw(account, withdrawRequest);
                    if(isComplete) {
                        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(FAILED, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(INSUFFICIENT_ACCOUNT_BALANCE, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/deposit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deposit(
            @Valid @RequestBody DepositRequest depositRequest) {
        LOGGER.debug("Triggered AccountRestController.deposit");
        if (ValidatorService.isDepositRequestValid(depositRequest)) {
            // TODO implement lock mechanism after getting account.
            Account account = accountService.getAccount(depositRequest.getTargetAccountNumber());
            if (account == null) {
                return new ResponseEntity<>(NO_ACCOUNT_FOUND, HttpStatus.OK);
            } else {
                //account.setCurrentBalance(account.getCurrentBalance().add(depositRequest.getAmount()));
                //boolean isComplete = accountService.updateBalance(account);
                boolean isComplete = transactionService.deposit(account, depositRequest);
                if(isComplete) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(FAILED, HttpStatus.OK);
                }
            }
        } else {
            return new ResponseEntity<>(INVALID_TRANSACTION, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}