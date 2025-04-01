package com.well.banco_digital_CDBW.dto;

import com.well.banco_digital_CDBW.entity.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoReqDto(
		@NotBlank(message = "O campo cep é obrigatorio.")
		@Pattern(regexp = "\\d{5}-\\d{3}", message = "Deve ser um cep no padrão valido.")
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

	public EnderecoReqDto(Endereco endereco) {
		this(endereco.getCep(), endereco.getCidade(), endereco.getEstado(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro());
	}

}
