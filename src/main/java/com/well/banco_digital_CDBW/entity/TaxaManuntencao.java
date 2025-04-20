package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TAXA MANUNTENCAO")
public class TaxaManuntencao extends Transacao{

	public TaxaManuntencao(Conta contaOrigem, BigDecimal valor) {
		super(contaOrigem, null, valor);
	}

	@Override
	public void aplicar() {
		getContaOrigem().debitar(getValor());
	}

}
