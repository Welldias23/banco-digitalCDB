package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PixDto(
		@NotNull(message = "O id da conta é obrigatorio.")
		Long contaId,
		@NotBlank(message = "A chave pix é obrigatoria.")
		String chavePix) {

}
