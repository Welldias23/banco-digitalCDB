package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.PagamentoCredito;

import jakarta.validation.constraints.NotNull;

public record FaturaDto(
		@JsonView(View.Detalhar.class)
		BigDecimal valorFatura,
		
		@JsonView(View.Resumo.class)
		BigDecimal valorPago,
		
		@JsonView(View.Detalhar.class)
		BigDecimal limiteDisponivel,
		
		@JsonView(View.Persistir.class)
		@NotNull(groups = Creat.class, message = "O valor a ser pago deve ser informado.")
		BigDecimal valor,

		@JsonView(View.Detalhar.class)
		List<PagamentoCredito> compras) {


}
