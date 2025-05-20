package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;

public record PixDto(
		@NotBlank(message = "A chave pix é obrigatoria.")
		String chavePix) {

}
