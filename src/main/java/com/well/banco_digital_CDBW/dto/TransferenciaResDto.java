package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.entity.TransferenciaPix;

public record TransferenciaResDto(
		String nomeOrigem,
		String nomeDestino,
		BigDecimal valor,
		LocalDate data,
		LocalTime horario) {

	public TransferenciaResDto(Transferencia transferencia) {
		this(transferencia.getNomeOrigem(), transferencia.getNomeDestino(), transferencia.getValor(), transferencia.getData(), transferencia.getHorario());
	}

	public TransferenciaResDto(TransferenciaPix transferenciaPix) {
		this(transferenciaPix.getNomeOrigem(), transferenciaPix.getNomeDestino(), transferenciaPix.getValor(), transferenciaPix.getData(), transferenciaPix.getHorario());
	}



}
