package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;

import com.well.banco_digital_CDBW.dto.PagamentoDto;
import com.well.banco_digital_CDBW.entity.Pagamento;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
	
	PagamentoDto toPagamentoDto(Pagamento pagamento);

}
