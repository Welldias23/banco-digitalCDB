package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.well.banco_digital_CDBW.entity.Deposito;

public record DepositoResDto(
		Long idContaDestino,
		String nomeDestino,
		BigDecimal valor) {

	public DepositoResDto(Deposito depositoFeito) {
		this(depositoFeito.getContaDestino().getId(), depositoFeito.getNomeDestino(), depositoFeito.getValor());
	}


}
