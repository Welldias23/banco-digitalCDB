package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CartaoDto(	
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Long id,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "A bandeira do cartão é obrigatoria escolha MasterCard ou Visa.")
		String bandeira,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "A senha do cartão é obrigatoria.")
		String senha,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		String numeroCartao,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		BigDecimal limiteDiario,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		BigDecimal limiteCredito,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		BigDecimal limiteCreditoUsado,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalDate dataCriacao,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalTime horaCriacao,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
