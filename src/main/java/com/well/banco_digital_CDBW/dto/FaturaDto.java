package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.util.List;

import com.well.banco_digital_CDBW.entity.PagamentoCredito;

public record FaturaDto(
		BigDecimal valor,
		List<PagamentoCredito> compras) {

}
