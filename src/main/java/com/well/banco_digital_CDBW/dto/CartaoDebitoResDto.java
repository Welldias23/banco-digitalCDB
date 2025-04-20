package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.entity.CartaoDebito;

public record CartaoDebitoResDto(	Long id,
		String numeroCartao,
		BigDecimal limiteDiario,
		LocalDate dataCriacao,
		LocalTime horaCriacao,
		Boolean ativo) {


	public CartaoDebitoResDto(CartaoDebito cartao) {
		this(cartao.getId(), 
				cartao.getNumeroCartao(), 
				cartao.getLimiteDiario(), 
				cartao.getDataCriacao(), 
				cartao.getHoraCriacao(), 
				cartao.getAtivo());
}


}
