package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.well.banco_digital_CDBW.entity.Transferencia;

public record TransferenciaResDto(
		String DeNome,
		String ParaNome,
		BigDecimal valor) {

	public TransferenciaResDto(Transferencia transferencia) {
		this(transferencia.getNomeOrigem(), transferencia.getNomeDestino(), transferencia.getValor());
	}

}
