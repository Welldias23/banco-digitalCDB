package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;

public record CartaoDto(	
		@JsonView(View.Detalhar.class)
		Long id,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "A bandeira do cartão é obrigatoria escolha MasterCard ou Visa.")
		String bandeira,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "A senha do cartão é obrigatoria.")
		String senha,
		
		@JsonView(View.Detalhar.class)
		String numeroCartao,
		
		@JsonView(View.Detalhar.class)
		BigDecimal limiteDiario,
		
		@JsonView(View.Detalhar.class)
		BigDecimal limiteCredito,
		
		@JsonView(View.Detalhar.class)
		BigDecimal limiteCreditoUsado,
		
		@JsonView(View.Detalhar.class)
		LocalDate dataCriacao,
		
		@JsonView(View.Detalhar.class)
		LocalTime horaCriacao,
		
		@JsonView(View.Detalhar.class)
		Boolean ativo) {



}
