package org.wonderlabz.banking.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequest {
    @NotBlank(message = "Source Account Number is mandatory")
    private String sourceAccountNumber;
    @NotBlank(message = "Target Account Id is mandatory")
    private String targetAccountNumber;
    @NotBlank(message = "Transaction Amount is mandatory")
    private String amount;
}
