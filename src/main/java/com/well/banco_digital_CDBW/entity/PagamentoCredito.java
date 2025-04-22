package com.well.banco_digital_CDBW.entity;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CREDITO")
public class PagamentoCredito extends Pagamento {
	

	@ManyToOne
	@JoinColumn(name = "cartao_credito_id")
	private CartaoCredito cartao;

	public PagamentoCredito(CartaoCredito cartao, PagamentoReqDto pagamentoReq) {
		super(pagamentoReq);
		this.cartao = cartao;
	}

	@Override
	public void pagar() {
		//pensar em como refatorar
		this.cartao.debitarNoLimite(getValor());
	}

}
