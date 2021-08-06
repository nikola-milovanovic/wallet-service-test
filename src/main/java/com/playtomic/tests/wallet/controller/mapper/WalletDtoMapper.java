package com.playtomic.tests.wallet.controller.mapper;

import com.playtomic.tests.wallet.controller.dto.response.WalletResponseDto;
import com.playtomic.tests.wallet.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletDtoMapper {

    WalletDtoMapper INSTANCE = Mappers.getMapper(WalletDtoMapper.class);

    WalletResponseDto toWalletResponseDto(Wallet wallet);

}