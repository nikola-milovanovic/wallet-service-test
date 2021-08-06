package com.playtomic.tests.wallet.controller;

import com.playtomic.tests.wallet.controller.dto.request.PaymentRequestDto;
import com.playtomic.tests.wallet.controller.dto.response.PaymentResponseDto;
import com.playtomic.tests.wallet.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class PaymentController {
    private final StripeService stripeService;

    @PutMapping("charge")
    public PaymentResponseDto charge(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return stripeService.charge(paymentRequestDto.getCreditCardNumber(), paymentRequestDto.getAmount());
    }

    @PutMapping("recharge")
    public PaymentResponseDto recharge(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return stripeService.recharge(paymentRequestDto.getCreditCardNumber(), paymentRequestDto.getAmount());
    }

}
