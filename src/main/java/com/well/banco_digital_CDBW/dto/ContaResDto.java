package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.util.Optional;

import com.well.banco_digital_CDBW.entity.Conta;

public record ContaResDto(	
		Long id,
		Long agencia,
		Long numeroConta,
		BigDecimal saldo,
		String chavePix,
		Boolean ativa) {

	public ContaResDto(Conta conta) {
		this(conta.getId(),conta.getAgencia(), conta.getNumeroConta(), conta.getSaldo(), conta.getChavePix(), conta.getAtiva());
	}

	public ContaResDto(Optional<Conta> conta) {
		this(conta.get().getId(),conta.get().getAgencia(), conta.get().getNumeroConta(), conta.get().getSaldo(), conta.get().getChavePix(),conta.get().getAtiva());
	}

}
