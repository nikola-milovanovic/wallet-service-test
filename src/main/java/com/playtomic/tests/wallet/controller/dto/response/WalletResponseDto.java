package com.playtomic.tests.wallet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletResponseDto {

    private String creditCardNumber;

    private BigDecimal balance;

}
