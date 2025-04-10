package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.CartaoCredito;

public record CartaoResDto(	
		Long id,
		Long numeroCartao,
		BigDecimal limiteDiario,
		BigDecimal limiteCredito,
		LocalDate dataCriacao,
		LocalTime horaCriacao,
		Boolean ativo) {

	public CartaoResDto(CartaoCredito cartao) {
		this(cartao.getId(), cartao.getNumeroCartao(), null, cartao.getLimiteCredito(), cartao.getDataCriacao(), cartao.getHoraCriacao(), cartao.getAtivo());
	}

}
