package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;

import jakarta.validation.constraints.NotBlank;

public record CartaoDto(	
		@JsonView(View.Get.class)
		Long id,
		
		@JsonView(View.Post.class)
		@NotBlank(groups = Complete.class, message = "A bandeira do cartão é obrigatoria escolha MasterCard ou Visa.")
		String bandeira,
		
		@JsonView(View.Post.class)
		@NotBlank(groups = Complete.class, message = "A senha do cartão é obrigatoria.")
		String senha,
		
		@JsonView(View.Get.class)
		String numeroCartao,
		
		@JsonView(View.Get.class)
		BigDecimal limiteDiario,
		
		@JsonView(View.Get.class)
		BigDecimal limiteCredito,
		
		@JsonView(View.Get.class)
		BigDecimal limiteCreditoUsado,
		
		@JsonView(View.Get.class)
		LocalDate dataCriacao,
		
		@JsonView(View.Get.class)
		LocalTime horaCriacao,
		
		@JsonView(View.Get.class)
		Boolean ativo) {

	public CartaoDto(CartaoCredito cartao) {
		this(cartao.getId(), 
				null,
				null,
				cartao.getNumeroCartao(), 
				null,
				cartao.getLimiteCreditoTotal(),
				cartao.getLimiteCreditoUsado(),
				cartao.getDataCriacao(), 
				cartao.getHoraCriacao(), 
				cartao.getAtivo());
	}
	
	public CartaoDto(CartaoDebito cartao) {
		this(cartao.getId(), 
				null,
				null,
				cartao.getNumeroCartao(), 
				cartao.getLimiteDiario(),
				null, 
				null,
				cartao.getDataCriacao(), 
				cartao.getHoraCriacao(), 
				cartao.getAtivo());
	}

	public CartaoDto(Cartao cartao) {
		this(cartao.getId(), 
				null,
				null,
				cartao.getNumeroCartao(), 
				null,
				null, 
				null,
				cartao.getDataCriacao(), 
				cartao.getHoraCriacao(), 
				cartao.getAtivo());
	}


}
