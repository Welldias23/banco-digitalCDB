package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Conta Poupanca")
@Getter
@Setter
public class ContaPoupanca extends Conta{

	private Double rendimento;
	
	public ContaPoupanca() {
		super();
	}
	
	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void gerarNumeroConta() {

		getNumeroConta();
		Long.sum(5011, getId());
		
	}
}
