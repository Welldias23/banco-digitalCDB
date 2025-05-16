package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotNull;

public record TransferenciaDto(
		@JsonView(View.Persistir.class)
		@NotNull(groups = Creat.class, message = "O id da conta de destino é obrigatorio.")
		Long idContaDestino,
		
		@JsonView(View.Detalhar.class)
		String nomeOrigem,
		
		@JsonView(View.Detalhar.class)
		String nomeDestino, 
		
		@JsonView({View.Persistir.class, View.Detalhar.class})
		@NotNull(groups = Creat.class, message = "O valor da tranferencia é obrigatorio.")
		BigDecimal valor,
		
		@JsonView(View.Detalhar.class)
		LocalDate data,
		
		@JsonView(View.Detalhar.class)
		LocalTime hora) {


}
