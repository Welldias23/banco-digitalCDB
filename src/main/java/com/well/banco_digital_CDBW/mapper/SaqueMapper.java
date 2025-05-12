package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.SaqueDto;
import com.well.banco_digital_CDBW.entity.Saque;

@Mapper(componentModel = "spring")
public interface SaqueMapper {

	SaqueDto toSaqueDto(Saque saque);
}
