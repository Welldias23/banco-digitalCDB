package com.well.banco_digital_CDBW.entity;


import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("TRANSFERENCIA")
@Getter
@Setter
public class Transferencia extends Transacao{
	
	public Transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		super(contaOrigem, contaDestino, valor);
	}

	@Override
	public void aplicar() {
		getContaDestino().creditar(getValor());
		getContaOrigem().debitar(getValor());
		
	}
	
	
}
