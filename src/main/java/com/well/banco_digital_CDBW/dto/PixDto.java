package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PixDto(
		@NotBlank(message = "A chave pix é obrigatoria.")
		String chavePix) {

}
