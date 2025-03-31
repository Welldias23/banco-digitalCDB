package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Conta Corrente")
public class ContaCorrente extends Conta{
	private Float taxaManutencao;
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

}
