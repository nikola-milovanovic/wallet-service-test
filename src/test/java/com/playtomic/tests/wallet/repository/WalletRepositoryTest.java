package com.playtomic.tests.wallet.repository;

import com.playtomic.tests.wallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith({SpringExtension.class})
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WalletRepositoryTest {

    @Autowired
    private WalletRepository walletRepository;

    @Test
    void testFindWalletByCreditCardNumberSuccess() {
        Optional<Wallet> optionalWallet = walletRepository.findWalletByCreditCardNumber("123-456");
        assertTrue(optionalWallet.isPresent());
    }

    @Test
    void testFindWalletByCreditCardNumberNotFound() {
        Optional<Wallet> optionalWallet = walletRepository.findWalletByCreditCardNumber("wrong_credit_card_number");
        assertTrue(optionalWallet.isEmpty());
    }

}
