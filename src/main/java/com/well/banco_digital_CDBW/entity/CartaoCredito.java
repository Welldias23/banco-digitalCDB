package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.util.List;

import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.exception.CategoriaNaoExisteException;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private BigDecimal limiteCreditoTotal;
	private BigDecimal limiteCreditoDisponivel;
	@OneToMany(mappedBy = "cartao")
	private List<PagamentoCredito> fatura;
	
	
	public CartaoCredito(Conta conta, Cliente cliente, String senha, String numeroCartao) {
		super(conta, senha, numeroCartao);
		this.taxaAnuidade = new BigDecimal("150.00");
		//tirar essa regra do construtor
		if(cliente.getCategoria() == CategoriaCliente.COMUM) {
			this.limiteCreditoTotal = new BigDecimal("1000.00");
		} else if(cliente.getCategoria() == CategoriaCliente.SUPER) {
			this.limiteCreditoTotal = new BigDecimal("5000.00");
		} else if(cliente.getCategoria() == CategoriaCliente.PREMIUM) {
			this.limiteCreditoTotal = new BigDecimal("10000.00");
		}else {
			throw new CategoriaNaoExisteException();
		}
		this.limiteCreditoDisponivel = this.limiteCreditoTotal;
		
	}


	public void alterarLimite(BigDecimal limite) {
		//colocar a logica 
		this.limiteCreditoTotal = limite;
		
	}


	public void debitarNoLimite(BigDecimal valor) {
		this.limiteCreditoDisponivel = limiteCreditoDisponivel.subtract(valor);
	}
	
	public BigDecimal calcularFatura() {
		return limiteCreditoDisponivel.subtract(limiteCreditoTotal).abs();
	}


	public void creditarNoLimite(BigDecimal valor) {
		this.limiteCreditoDisponivel = limiteCreditoDisponivel.add(valor);
	}
	
}
