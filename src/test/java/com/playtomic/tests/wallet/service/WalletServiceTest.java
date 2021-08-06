package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.exception.BadRequestException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @MockBean
    private WalletRepository walletRepository;

    @Test
    @DisplayName("Test getWalletByCreditCardNumberSuccess")
    void testGetWalletByCreditCardNumberSuccess() {
        Wallet mockWallet = new Wallet("1", "123-456", new BigDecimal(10));
        doReturn(Optional.of(mockWallet)).when(walletRepository).findWalletByCreditCardNumber("123-456");
        Wallet wallet = walletService.getWalletByCreditCardNumber("123-456");
        assertNotNull(wallet);
    }

    @Test
    @DisplayName("Test getWalletByCreditCardNumberNotFound")
    void testGetWalletByCreditCardNumberNotFound() {
        doReturn(Optional.empty()).when(walletRepository).findWalletByCreditCardNumber("wrong_credit_card_number");
        assertThrows(BadRequestException.class, () -> walletService.getWalletByCreditCardNumber("wrong_credit_card_number"));
    }

}
