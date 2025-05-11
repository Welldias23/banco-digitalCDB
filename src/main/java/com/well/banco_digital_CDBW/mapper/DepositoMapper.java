package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.DepositoDto;
import com.well.banco_digital_CDBW.entity.Deposito;

@Mapper(componentModel = "spring")
public interface DepositoMapper {
	
	DepositoDto toDepositoDto(Deposito deposito);
	
}
