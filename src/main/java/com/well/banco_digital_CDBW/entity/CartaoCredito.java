package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CARTAO DE CREDITO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoCredito extends Cartao{

	private BigDecimal taxaAnuidade;
	private BigDecimal limiteCredito;
	
	
	public CartaoCredito(Object conta, Cliente cliente) {
		super(conta);
		this.taxaAnuidade = new BigDecimal("150.00");
		this.limiteCredito = cliente.getRendaMensal();
	}
	
}
