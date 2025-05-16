package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.entity.CartaoDebito;

@Mapper(componentModel = "spring")
public interface CartaoDebitoMapper {
	
	CartaoDto toCartaoDto(CartaoDebito cartao);

}
