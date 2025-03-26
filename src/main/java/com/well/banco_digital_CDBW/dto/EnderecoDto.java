package com.well.banco_digital_CDBW.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDto(
		@NotBlank(message = "O campo cep é obrigatorio.")
		String cep,
		@NotBlank(message = "O campo cidade é obrigatorio.")
		String cidade,
		@NotBlank(message = "O campo estado é obrigatorio.")
		String estado,
		@NotBlank(message = "O campo rua é obrigatorio.")
        String rua, 
        @NotNull(message = "O campo número é obrigatorio.")
        Integer numero,
        String complemento,
        @NotBlank(message = "O campo bairro é obrigatorio.")
        String bairro) {

}
