package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.Saque;

public record SaqueResDto(
		BigDecimal valor,
		LocalDate data,
		LocalTime horario) {

	public SaqueResDto(Saque saque) {
		this(saque.getValor(), saque.getData(), saque.getHorario());
	}

}
