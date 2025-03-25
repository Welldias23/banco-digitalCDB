package com.well.banco_digital_CDBW.dto;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Endereco;

public record ClienteAtualizadoDto(
		Long id,
		String nome,
		String senha,
		LocalDate dataNascimento,
		Endereco endereco) {

}