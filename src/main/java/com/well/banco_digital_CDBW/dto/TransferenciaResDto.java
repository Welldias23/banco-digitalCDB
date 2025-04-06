package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Transferencia;

public record TransferenciaResDto(
		String nomeOrigem,
		String nomeDestino,
		BigDecimal valor,
		LocalDate horario) {

	public TransferenciaResDto(Transferencia transferencia) {
		this(transferencia.getNomeOrigem(), transferencia.getNomeDestino(), transferencia.getValor(), transferencia.getHorario());
	}

}
