package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record ClienteAtualizadoDto(
		String nome,
		@Email(message = "Deve ser um emai valido.")
		String email,
		@Size(min = 6, message = "A senha deve ter no minimo 6 digitos.")
		String senha,
		@Past(message = "A data de nascimento deve estar no passado.")
		LocalDate dataNascimento,
		BigDecimal rendaMensal,
		EnderecoDto endereco) {

}