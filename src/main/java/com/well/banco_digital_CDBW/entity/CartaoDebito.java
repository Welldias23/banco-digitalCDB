package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CARTAO DE DEBITO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartaoDebito extends Cartao{

	private BigDecimal limiteDiario;
	

	public CartaoDebito(Conta conta, String numeroCartao) {
		super(conta, numeroCartao);
		this.limiteDiario = new BigDecimal("200.00");
	}


	public void alterarLimiteDiario(BigDecimal novoLimite) {
		this.limiteDiario = novoLimite;
		
	}

}
