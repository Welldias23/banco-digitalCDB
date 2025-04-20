package com.well.banco_digital_CDBW.dto;

import com.well.banco_digital_CDBW.entity.Conta;

public record TransacaoDto(
		Conta contaOrigem,
		String nomeOrigem,
		Conta contaDestino,
		String nomeDestino) {

}
