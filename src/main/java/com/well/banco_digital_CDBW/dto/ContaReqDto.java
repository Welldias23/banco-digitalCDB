package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;

public record ContaReqDto(
		@NotBlank(message = "Você deve informar o tipo da: conta corrente ou conta poupança")
		String tipoConta
		) {

}
