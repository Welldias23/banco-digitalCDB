package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record TransferenciaPixReqDto(
		@NotNull(message = "A chave pix é obrigatorio.")
		String chavePix,
		@NotNull(message = "O valor da tranferencia é obrigatorio.")
		BigDecimal valor) {

}
