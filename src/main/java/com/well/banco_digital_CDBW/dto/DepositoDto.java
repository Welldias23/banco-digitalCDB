package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotNull;

public record DepositoDto(
		@JsonView({View.Persistir.class, View.Detalhar.class})
		@NotNull(groups = Creat.class, message = "O id da conta de destino é obrigatorio.")
		Long idContaDestino,
		
		@JsonView(View.Detalhar.class)
		String nomeDestino,

		@JsonView({View.Persistir.class, View.Detalhar.class})
		@NotNull(groups = Creat.class,message = "O valor da tranferencia é obrigatorio.")
		BigDecimal valor) {


}
