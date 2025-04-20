package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public enum CategoriaCliente {
	COMUM(new BigDecimal("12.00")),
	SUPER(new BigDecimal("8.00")),
	PREMIUM(new BigDecimal("0.00"));
	
	private final BigDecimal taxaManuntencao;

	CategoriaCliente(BigDecimal taxaManuntencao) {
		this.taxaManuntencao = taxaManuntencao;
	}
}
