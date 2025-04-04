package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
}
