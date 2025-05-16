package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.well.banco_digital_CDBW.dto.DepositoDto;
import com.well.banco_digital_CDBW.entity.Deposito;

@Mapper(componentModel = "spring")
public interface DepositoMapper {
	
	@Mapping(source = "contaDestino.id", target = "idContaDestino")
	DepositoDto toDepositoDto(Deposito deposito);
	
}
