package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.well.banco_digital_CDBW.exception.CategoriaNaoExisteException;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
	private BigDecimal limiteCreditoUsado;
	@OneToMany(mappedBy = "cartao")
	private List<PagamentoCredito> fatura;
	
	
	public CartaoCredito(Conta conta, String senha, String numeroCartao) {
		super(conta, senha, numeroCartao);
		this.taxaAnuidade = new BigDecimal("150.00");
		
	}


	public void alterarLimite(BigDecimal limite) {
		//colocar a logica 
		this.limiteCreditoTotal = limite;
	}
	
	public void calcularLimiteInicial(Cliente cliente) {
		this.limiteCreditoTotal = Optional.ofNullable(cliente.getCategoria())
				.map(c -> c.equals(CategoriaCliente.COMUM) ? new BigDecimal("1000")
						: c.equals(CategoriaCliente.SUPER) ? new BigDecimal("5000")
						: c.equals(CategoriaCliente.PREMIUM) ? new BigDecimal("10000")
						: new BigDecimal("200"))
						.orElseThrow(() -> new CategoriaNaoExisteException());
		
		this.limiteCreditoUsado = new BigDecimal("0");
	}


	public void debitarNoLimite(BigDecimal valor) {
		this.limiteCreditoUsado = limiteCreditoUsado.add(valor);
	}


	public void creditarNoLimite(BigDecimal valor) {
		this.limiteCreditoUsado = limiteCreditoUsado.subtract(valor);
	}
	
}
