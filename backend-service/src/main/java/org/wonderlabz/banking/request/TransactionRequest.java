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
public class TransactionRequest {
    @NotBlank(message = "Transaction Type is mandatory")
    private String transactionType;
    @NotBlank(message = "Transaction Amount is mandatory")
    private String amount;
}
