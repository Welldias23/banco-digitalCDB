package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Conta Poupanca")
public class ContaPoupanca extends Conta{

	private Float  rendimento;
	
	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}
}
