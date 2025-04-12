package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.CartaoCredito;

public record CartaoCreditoResDto(	
		Long id,
		String numeroCartao,
		BigDecimal limiteCredito,
		LocalDate dataCriacao,
		LocalTime horaCriacao,
		Boolean ativo) {

	public CartaoCreditoResDto(CartaoCredito cartao) {
		this(cartao.getId(), 
				cartao.getNumeroCartao(), 
				cartao.getLimiteCredito(), 
				cartao.getDataCriacao(), 
				cartao.getHoraCriacao(), 
				cartao.getAtivo());
	}


}
