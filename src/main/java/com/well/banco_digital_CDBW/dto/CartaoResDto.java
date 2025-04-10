package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;

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

	public CartaoResDto(CartaoDebito cartao) {
		this(cartao.getId(), cartao.getNumeroCartao(), cartao.getLimiteDiario(), null, cartao.getDataCriacao(), cartao.getHoraCriacao(), cartao.getAtivo());
	}


}
