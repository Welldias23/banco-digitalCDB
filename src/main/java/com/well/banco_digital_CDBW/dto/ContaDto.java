package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaDto(
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "Você deve informar o tipo da: conta corrente ou conta poupança")
		String tipoConta,

		@JsonView(View.Persistir.class)
		@NotNull(groups = Creat.class, message = "Você deve informar a bandeira e a senha do cartao")
		CartaoDto cartaoDebito,
		
		@JsonView(View.Detalhar.class)
		Long id,
		
		@JsonView(View.Detalhar.class)
		Long agencia,
		
		@JsonView(View.Detalhar.class)
		String numeroConta,
		
		@JsonView(View.Resumo.class)
		BigDecimal saldo,
		
		@JsonView(View.Detalhar.class)
		String chavePix,
		
		@JsonView(View.Detalhar.class)
		Boolean ativa
		) {

}
