package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

public record SaqueDto(
		@JsonView({View.Persistir.class, View.Detalhar.class})
		@NotNull(groups = Creat.class, message = "O valor do saque deve ser informado.")
		BigDecimal valor,
		
		@JsonView(View.Detalhar.class)
		LocalDate data,
		
		@JsonView(View.Detalhar.class)
		LocalTime hora) {
}
