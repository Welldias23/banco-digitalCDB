package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.Pagamento;

public record PagamentoResDto(
		Long id,
		String nomeEstabelecimento,
		String nomeObjeto,
		BigDecimal valor,
		LocalDate data,
		LocalTime hora) {

	public PagamentoResDto(Pagamento pagamento) {
		this(pagamento.getId(), pagamento.getNomeEstabelecimento(), pagamento.getNomeObjeto(), pagamento.getValor(), pagamento.getData(), pagamento.getHora());
	}

}
