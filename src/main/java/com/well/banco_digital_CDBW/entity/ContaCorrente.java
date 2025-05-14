package com.well.banco_digital_CDBW.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CORRENTE")
@Getter
@Setter
public class ContaCorrente extends Conta{
	
	public ContaCorrente() {
	    super(); 
	}
	
	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}

}
