package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;

public record CartaoReqDto(
		@NotBlank(message = "A bandeira do cartão é obrigatoria escolha MasterCard ou Visa.")
		String bandeira,
		@NotBlank(message = "A senha do cartão é obrigatoria.")
		String senha) {

}
