package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Conta;

import jakarta.validation.constraints.NotBlank;

public record ContaDto(
		@JsonView(View.Post.class)
		@NotBlank(groups = Complete.class, message = "Você deve informar o tipo da: conta corrente ou conta poupança")
		String tipoConta,
		
		@JsonView(View.Get.class)
		Long id,
		
		@JsonView(View.Get.class)
		Long agencia,
		
		@JsonView(View.Get.class)
		Long numeroConta,
		
		@JsonView(View.Get.class)
		BigDecimal saldo,
		
		@JsonView(View.Get.class)
		String chavePix,
		
		@JsonView(View.Get.class)
		Boolean ativa
		) {

	public ContaDto(Conta conta) {
		this(conta.getClass().getSimpleName(), 
				conta.getId(), 
				conta.getAgencia(), 				
				conta.getNumeroConta(), 
				conta.getSaldo(), 
				conta.getChavePix(), 
				conta.getAtiva());
	}

}
