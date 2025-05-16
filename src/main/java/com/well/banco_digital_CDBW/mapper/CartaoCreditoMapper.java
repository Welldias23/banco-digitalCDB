package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.entity.CartaoCredito;

@Mapper(componentModel = "spring")
public interface CartaoCreditoMapper {

	@Mapping(source = "limiteCreditoTotal", target = "limiteCredito")
	CartaoDto toCartaoDto(CartaoCredito cartaoCredito);
	
}
