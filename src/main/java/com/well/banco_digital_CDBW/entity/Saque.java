package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAQUE")
public class Saque extends Transacao{

	public Saque(Conta contaOrigem, BigDecimal valor) {
		super(contaOrigem, null, valor);
	}
	
	@Override
	public void aplicar() {
		getContaOrigem().debitar(getValor());
		
	}

	public Saque() {
		
	}
	
}
