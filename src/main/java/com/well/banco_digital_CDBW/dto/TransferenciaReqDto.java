package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record TransferenciaReqDto(
		@NotNull(message = "O id da conta de destino é obrigatorio.")
		Long idContaDestino,
		@NotNull(message = "O valor da tranferencia é obrigatorio.")
		BigDecimal valor) {

}
