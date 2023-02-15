package org.wonderlabz.banking.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountRequest {

    @NotBlank(message = "Account number is mandatory")
    @Pattern(regexp = "^[0-9]{8}$", message = "Account number is Invalid")
    private String accountNumber;
    @NotBlank(message = "Account Type is mandatory")
    private String accountType;
    private String openingBalance;
    @NotBlank(message = "User Name is mandatory")
    private String userName;

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", openingBalance=" + openingBalance +
                ", userName='" + userName + '\'' +
                '}';
    }

}