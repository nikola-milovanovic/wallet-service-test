package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.exception.BadRequestException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private Logger log = LoggerFactory.getLogger(WalletService.class);
    private final WalletRepository walletRepository;

    public void save(Wallet wallet) {
        walletRepository.saveAndFlush(wallet);
    }

    public Wallet getWalletByCreditCardNumber(String creditCardNumber) {
        Optional<Wallet> optionalWallet = walletRepository.findWalletByCreditCardNumber(creditCardNumber);
        return optionalWallet.orElseThrow(() -> {
            log.warn("Bad request, wrong wallet credit card number entered: {}", creditCardNumber);
            return new BadRequestException("Wallet with credit card number: " + creditCardNumber + " doesn't exist");
        });
    }

}
