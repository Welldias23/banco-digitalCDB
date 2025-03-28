package com.well.banco_digital_CDBW.dto;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Cliente;
public record ClienteDto(
		Long id,
		String nome,
		LocalDate dataNascimento,
		EnderecoDto endereco) {

	public ClienteDto(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getDataNascimento(), new EnderecoDto(cliente.getEndereco()));
	}



}