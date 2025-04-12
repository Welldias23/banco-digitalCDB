package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import com.well.banco_digital_CDBW.exception.CategoriaNaoExisteException;

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
	
	
	public CartaoCredito(Conta conta, Cliente cliente, String senha, String numeroCartao) {
		super(conta, senha, numeroCartao);
		this.taxaAnuidade = new BigDecimal("150.00");
		if(cliente.getCategoria() == CategoriaCliente.COMUM) {
			this.limiteCredito = new BigDecimal("1000.00");
		} else if(cliente.getCategoria() == CategoriaCliente.SUPER) {
			this.limiteCredito = new BigDecimal("5000.00");
		} else if(cliente.getCategoria() == CategoriaCliente.PREMIUM) {
			this.limiteCredito = new BigDecimal("10000.00");
		}else {
			throw new CategoriaNaoExisteException();
		}
		
	}


	public void alterarLimite(BigDecimal limite) {
		//colocar a logica 
		this.limiteCredito = limite;
		
	}
	
}
