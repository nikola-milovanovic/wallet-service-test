package com.playtomic.tests.wallet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentResponseDto {

    private String creditCardNumber;

    private BigDecimal amount;

}
