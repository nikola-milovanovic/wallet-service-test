package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.controller.dto.response.PaymentResponseDto;
import com.playtomic.tests.wallet.exception.BadRequestException;
import com.playtomic.tests.wallet.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * Handles the communication with Stripe.
 * <p>
 * A real implementation would call to String using their API/SDK.
 * This dummy implementation throws an error when trying to charge less than 10€.
 */
@Service
@RequiredArgsConstructor
public class StripeService {
    private static final BigDecimal THRESHOLD_CHARGE = new BigDecimal(10);
    private static final BigDecimal THRESHOLD_RECHARGE = new BigDecimal(1);
    private final WalletService walletService;

    /**
     * Charges money in the credit card.
     *
     * @param creditCardNumber The number of the credit card
     * @param amount           The amount that will be charged.
     */
    public PaymentResponseDto charge(String creditCardNumber, BigDecimal amount) {
        Wallet wallet = walletService.getWalletByCreditCardNumber(creditCardNumber);
        validateRequest(amount, wallet);
        subtractFromBalance(wallet, amount);
        return createPaymentResponse(wallet);
    }

    private void validateRequest(BigDecimal amount, Wallet wallet) {
        if (amount.compareTo(THRESHOLD_CHARGE) < 0) {
            throw new BadRequestException("Bad Request - amount must be bigger than 10€");
        }
        if (amount.compareTo(wallet.getBalance()) > 0) {
            throw new BadRequestException("Bad Request - not enough money to charge, on balance left: " + wallet.getBalance());
        }
    }

    private void subtractFromBalance(Wallet wallet, BigDecimal amount) {
        BigDecimal newBalance = wallet.getBalance().subtract(amount);
        wallet.setBalance(newBalance);
        walletService.save(wallet);
    }

    /**
     * Recharges money in the credit card.
     *
     * @param creditCardNumber The number of the credit card
     * @param amount           The amount that will be charged.
     */
    public PaymentResponseDto recharge(String creditCardNumber, BigDecimal amount) {
        Wallet wallet = walletService.getWalletByCreditCardNumber(creditCardNumber);
        validateRequest(amount);
        addToBalance(wallet, amount);
        return createPaymentResponse(wallet);
    }

    private void validateRequest(BigDecimal amount) {
        if (amount.compareTo(THRESHOLD_RECHARGE) < 0) {
            throw new BadRequestException("Bad Request - amount must be positive number");
        }
    }

    private void addToBalance(Wallet wallet, BigDecimal amount) {
        BigDecimal newBalance = wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);
        walletService.save(wallet);
    }

    private PaymentResponseDto createPaymentResponse(Wallet wallet) {
        return new PaymentResponseDto(wallet.getCreditCardNumber(), wallet.getBalance());
    }

}
