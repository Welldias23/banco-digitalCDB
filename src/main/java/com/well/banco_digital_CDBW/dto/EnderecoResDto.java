package com.well.banco_digital_CDBW.dto;

import com.well.banco_digital_CDBW.entity.Endereco;

public record EnderecoResDto(
		String cep,
		String cidade,
		String estado,
        String rua, 
        Integer numero,
        String complemento,
        String bairro) {

	public EnderecoResDto(Endereco endereco) {
		this(endereco.getCep(), endereco.getCidade(), endereco.getEstado(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro());
	}

}
