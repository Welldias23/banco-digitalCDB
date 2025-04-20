package com.well.banco_digital_CDBW.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(	
		@NotBlank(message = "O campo cpf é obrigatorio.")
		@CPF
		String cpf,		
		@NotBlank(message = "O campo senha é obrigatorio.")
		String senha) {

}
