package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.entity.Conta;

@Mapper(componentModel = "spring")
public interface ContaMapper {
	
	ContaDto toContaDto(Conta conta);
}
