package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record PagamentoFatura(
		@NotNull(message = "O valor a ser pago deve ser informado.")
		BigDecimal valor) {

}
