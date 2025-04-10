package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;

public record NovaSenhaDto(
		@NotBlank(message = "A nova senha é obirgatoria.")
		String novaSenha) {

}
