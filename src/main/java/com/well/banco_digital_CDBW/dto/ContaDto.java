package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaDto(
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "Você deve informar o tipo da: conta corrente ou conta poupança")
		String tipoConta,

		@JsonView(View.Persistir.class)
		@NotNull(groups = Creat.class, message = "Você deve informar a bandeira e a senha do cartao")
		CartaoDto cartao,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Long id,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Long agencia,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		String numeroConta,
		
		@JsonView(View.Resumo.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		BigDecimal saldo,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		String chavePix,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Boolean ativa
		) {

}
