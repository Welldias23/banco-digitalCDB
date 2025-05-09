package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.EnderecoDto;
import com.well.banco_digital_CDBW.entity.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
	
	Endereco toEndereco(EnderecoDto enderecoDto);
	
	EnderecoDto toEnderecoDto(Endereco endereco);
}
