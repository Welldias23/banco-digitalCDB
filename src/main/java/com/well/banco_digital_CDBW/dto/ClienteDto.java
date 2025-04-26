package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDto(
		@JsonView(View.Get.class)
		Long id,
		
		@JsonView({View.Get.class, View.Patch.class, View.Post.class, View.Put.class})
		@NotBlank(groups = Complete.class, message = "O campo nome é obrigatorio.")
		@Size(min = 2, max = 200, message = "o campo nome deve conter no mínimo 2 caracteres e máximo de 100 caracteres.")
		@Pattern(regexp = "^[a-zA-ZÀ-ú ]+$", message = "O campo nome deve conter apenas letras.")
		String nome,
		
		@JsonView({View.Get.class, View.Patch.class, View.Post.class, View.Put.class})
		@NotBlank(groups = Complete.class, message = "O campo cpf é obrigatorio.")
		@CPF
		String cpf,
		
		@JsonView({View.Get.class, View.Patch.class, View.Post.class, View.Put.class})
		@NotBlank(groups = Complete.class, message = "O campo email é obrigatorio.")
		@Email(message = "Deve ser um emai valido.")
		String email,
		
		@JsonView({View.Patch.class, View.Post.class, View.Put.class})
		@NotBlank(groups = Complete.class, message = "O campo senha é obrigatorio.")
		@Size(min = 6, message = "A senha deve ter no minimo 6 digitos.")
		String senha,
		
		@JsonView({View.Get.class, View.Patch.class, View.Post.class, View.Put.class})
		@NotNull(groups = Complete.class, message = "O campo data de nascimento é obrigatorio.")
		@Past(message = "A data de nascimento deve estar no passado.")
		LocalDate dataNascimento,
		
		@JsonView({View.Get.class, View.Patch.class, View.Post.class, View.Put.class})
		@NotNull(groups = Complete.class, message = "O campo renda mensal é obrigatorio.")
		BigDecimal rendaMensal) {

	public ClienteDto(Cliente cliente) {
		this(cliente.getId(), 
				cliente.getNome(), 
				cliente.getCpf(), 
				cliente.getEmail(), 
				cliente.getSenha(), 
				cliente.getDataNascimento(), 
				cliente.getRendaMensal());
	}

}
