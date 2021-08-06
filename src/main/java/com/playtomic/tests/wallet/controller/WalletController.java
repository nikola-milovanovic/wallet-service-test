package com.playtomic.tests.wallet.controller;

import com.playtomic.tests.wallet.controller.dto.response.WalletResponseDto;
import com.playtomic.tests.wallet.controller.mapper.WalletDtoMapper;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/{creditCardNumber}")
    public WalletResponseDto getWallet(@PathVariable String creditCardNumber) {
        Wallet wallet = walletService.getWalletByCreditCardNumber(creditCardNumber);
        return WalletDtoMapper.INSTANCE.toWalletResponseDto(wallet);
    }

}
