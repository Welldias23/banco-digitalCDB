package com.well.banco_digital_CDBW.entity;


import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("deposito")
@Getter
@Setter
public class Deposito extends Transacao{
	
	public Deposito(Conta contaDestino, BigDecimal valor) {
		super(contaDestino, valor);
	}

}
