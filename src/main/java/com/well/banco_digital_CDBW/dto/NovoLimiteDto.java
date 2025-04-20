package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record NovoLimiteDto(
		@NotNull(message = "O novo limite desejado é obrigatorio.")
		BigDecimal novoLimite) {

}
