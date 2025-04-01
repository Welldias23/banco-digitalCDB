package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
		@NotBlank(message = "O campo nome é obrigatorio.")
		@Size(min = 2, max = 200, message = "o campo nome deve conter no mínimo 2 caracteres e máximo de 100 caracteres.")
		@Pattern(regexp = "^[a-zA-ZÀ-ú ]+$", message = "O campo nome deve conter apenas letras.")
		String nome,
		@NotBlank(message = "O campo cpf é obrigatorio.")
		@CPF
		String cpf,
		@NotBlank(message = "O campo email é obrigatorio.")
		@Email(message = "Deve ser um emai valido.")
		String email,
		@NotBlank(message = "O campo senha é obrigatorio.")
		@Size(min = 6, message = "A senha deve ter no minimo 6 digitos.")
		String senha,
		@Past(message = "A data de nascimento deve estar no passado.")
		LocalDate dataNascimento,
		@NotNull(message = "O campo renda mensal é obrigatorio.")
		BigDecimal rendaMensal,
		EnderecoReqDto endereco) {

}
