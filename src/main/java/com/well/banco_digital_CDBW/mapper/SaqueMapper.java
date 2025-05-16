package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.well.banco_digital_CDBW.dto.SaqueDto;
import com.well.banco_digital_CDBW.entity.Saque;

@Mapper(componentModel = "spring")
public interface SaqueMapper {

	@Mapping(source = "dataTransacao", target = "data")
	@Mapping(source = "horarioTransacao", target = "hora")
	SaqueDto toSaqueDto(Saque saque);
}
