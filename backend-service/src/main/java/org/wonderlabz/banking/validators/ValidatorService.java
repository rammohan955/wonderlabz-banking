package org.wonderlabz.banking.validators;

import org.wonderlabz.banking.entity.Account;
import org.wonderlabz.banking.request.CreateAccountRequest;
import org.wonderlabz.banking.request.DepositRequest;
import org.wonderlabz.banking.request.TransferRequest;
import org.wonderlabz.banking.request.WithdrawRequest;
import org.wonderlabz.banking.types.AccountType;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ValidatorService {

    public static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("^[0-9]{8}$");
    public static boolean isCreateAccountValid(CreateAccountRequest createAccountRequest) {
        if(!ACCOUNT_NUMBER_PATTERN.matcher(createAccountRequest.getAccountNumber()).find()){
            return false;
        }
        // inlcude existing account number validation
        return true;
    }

    public static boolean isTransferRequestValid(TransferRequest transferRequest) {
        return true;
    }

    public static boolean isWithdrawRequestValid(WithdrawRequest withdrawRequest) {
        return true;
    }

    public static boolean isDepositRequestValid(DepositRequest depositRequest) {
        return true;
    }

    public static boolean isAmountAvailable(Account account, TransferRequest transferRequest) {
        if(AccountType.SAVING.toString().equalsIgnoreCase(account.getAccountType())) {
            BigDecimal minBalance = new BigDecimal(1000);
            return account.getCurrentBalance().subtract(new BigDecimal(transferRequest.getAmount())).compareTo(minBalance) > 0;
        } else if(AccountType.CURRENT.toString().equalsIgnoreCase(account.getAccountType())) {
            BigDecimal overdraft = new BigDecimal(-150000);
            return account.getCurrentBalance().subtract(new BigDecimal(transferRequest.getAmount())).compareTo(overdraft) > 0;
        }
        return false;
    }

    public static boolean isAmountAvailable(Account account, BigDecimal amount) {
        if(AccountType.SAVING.toString().equalsIgnoreCase(account.getAccountType())) {
            BigDecimal minBalance = new BigDecimal(1000);
            return account.getCurrentBalance().subtract(amount).compareTo(minBalance) > 0;
        } else if(AccountType.CURRENT.toString().equalsIgnoreCase(account.getAccountType())) {
            BigDecimal overdraft = new BigDecimal(-150000);
            return account.getCurrentBalance().subtract(amount).compareTo(overdraft) > 0;
        }
        return false;
    }
}
