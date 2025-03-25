package com.well.banco_digital_CDBW.dto;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Endereco;

public record ClienteRequest(
		Long cpf,
		String nome,
		String senha,
		LocalDate dataNascimento,
		Endereco endereco) {

}
