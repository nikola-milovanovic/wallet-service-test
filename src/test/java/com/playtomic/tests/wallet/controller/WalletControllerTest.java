package com.playtomic.tests.wallet.controller;

import com.playtomic.tests.wallet.exception.BadRequestException;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class WalletControllerTest {

    @MockBean
    private WalletService walletService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /admin/wallet/{creditCardNumber} - Found")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetWalletFound() throws Exception {
        Wallet mockWallet = new Wallet("1", "123-456", new BigDecimal(10));
        doReturn(mockWallet).when(walletService).getWalletByCreditCardNumber("123-456");
        mockMvc.perform(get("/admin/wallet/{creditCardNumber}", "123-456"))
                .andExpect(jsonPath("$.creditCardNumber").value("123-456"))
                .andExpect(jsonPath("$.balance").value(new BigDecimal(10)));
    }

    @Test
    @DisplayName("GET /admin/wallet/{creditCardNumber} - Not Found")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetWalletNotFound() throws Exception {
        doThrow(BadRequestException.class).when(walletService).getWalletByCreditCardNumber("wrong_credit_card_number");
        mockMvc.perform(get("/admin/wallet/{creditCardNumber}", "wrong_credit_card_number"))
                .andExpect(status().isBadRequest());
    }

}
