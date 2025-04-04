package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.util.Optional;

import com.well.banco_digital_CDBW.entity.Conta;

public record ContaResDto(	
		Long id,
		BigDecimal saldo,
		Boolean ativa) {

	public ContaResDto(Conta conta) {
		this(conta.getId(), conta.getSaldo(),conta.getAtiva());
	}

	public ContaResDto(Optional<Conta> conta) {
		this(conta.get().getId(), conta.get().getSaldo(),conta.get().getAtiva());
	}

}
