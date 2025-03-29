package com.well.banco_digital_CDBW.dto;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Endereco;

import jakarta.validation.constraints.Size;

public record ClienteAtualizadoDto(
		Long id,
		String nome,
		@Size(min = 6, message = "A senha deve ter no minimo 6 digitos.")
		String senha,
		LocalDate dataNascimento,
		Endereco endereco) {

}