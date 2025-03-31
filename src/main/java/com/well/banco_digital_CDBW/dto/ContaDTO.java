package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;

public record ContaDTO(	
		Long id,
		BigDecimal saldo,
		Cliente cliente,
		Boolean ativa) {

	public ContaDTO(Conta conta) {
		this(conta.getId(), conta.getSaldo(), conta.getCliente(), conta.getAtiva());
	}

}
