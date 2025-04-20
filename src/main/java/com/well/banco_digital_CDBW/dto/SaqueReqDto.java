package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record SaqueReqDto(
		@NotNull(message = "O valor do saque deve ser informado.")
		BigDecimal valor) {

}
