package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
	private BigDecimal limiteDiarioUsado;
	@OneToMany(mappedBy = "cartao")
	private List<PagamentoDebito> compras;
	

	public CartaoDebito(Conta conta, String numeroCartao) {
		super(conta, numeroCartao);
		this.limiteDiario = new BigDecimal("200.00");
	}


	public void alterarLimiteDiario(BigDecimal novoLimite) {
		this.limiteDiario = novoLimite;
		
	}
	
	public void diminuirLimiteDiario(BigDecimal valor) {
		this.limiteDiarioUsado = this.limiteDiarioUsado.subtract(valor);
	}

}
