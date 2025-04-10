package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("taxa manuntecao")
public class Manuntencao extends Transferencia{

	public Manuntencao(Conta contaOrigem, BigDecimal valor) {
		super(contaOrigem, valor);
	}

}
