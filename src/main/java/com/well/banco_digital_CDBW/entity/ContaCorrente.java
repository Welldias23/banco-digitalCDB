package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Conta Corrente")
@Getter
@Setter
public class ContaCorrente extends Conta{
	private Double taxaManutencao;
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

}
