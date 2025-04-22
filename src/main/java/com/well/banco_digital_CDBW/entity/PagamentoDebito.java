package com.well.banco_digital_CDBW.entity;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DEBITO")
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDebito extends Pagamento {
	

	@ManyToOne
	@JoinColumn(name = "cartao_debito_id")
	private CartaoDebito cartao;

	public PagamentoDebito(CartaoDebito cartao, PagamentoReqDto pagamentoReq) {
		super(pagamentoReq);
		this.cartao = cartao;
	}
	
	@Override
	public void pagar() {
		this.cartao.getConta().debitar(getValor());

	}

}
