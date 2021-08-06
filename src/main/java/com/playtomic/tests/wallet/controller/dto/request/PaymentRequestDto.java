package com.playtomic.tests.wallet.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PaymentRequestDto {

    @NotNull(message = "Credit card number must be entered")
    private String creditCardNumber;

    @NotNull(message = "Amount must be entered")
    private BigDecimal amount;

}
