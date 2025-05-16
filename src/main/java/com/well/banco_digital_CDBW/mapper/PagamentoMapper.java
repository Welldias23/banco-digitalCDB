package com.well.banco_digital_CDBW.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.well.banco_digital_CDBW.dto.PagamentoDto;
import com.well.banco_digital_CDBW.entity.Pagamento;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
	
	@Mapping(source = "dataPagamento", target = "data")
	@Mapping(source = "horaPagamento", target = "hora")
	PagamentoDto toPagamentoDto(Pagamento pagamento);

}
