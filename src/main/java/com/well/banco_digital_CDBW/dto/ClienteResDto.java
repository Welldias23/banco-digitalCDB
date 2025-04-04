package com.well.banco_digital_CDBW.dto;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.entity.Cliente;
public record ClienteResDto(
		Long id,
		String nome,
		LocalDate dataNascimento) {

	public ClienteResDto(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getDataNascimento());
	}



}