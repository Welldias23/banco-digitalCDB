package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.entity.Transacao;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

	TransferenciaDto toTransferenciaDto(Transacao transacao);
	
}
