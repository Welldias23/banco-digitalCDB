package com.well.banco_digital_CDBW.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.FormaDePagamentoInvalidaException;
import com.well.banco_digital_CDBW.strategy.PagamentoCartaoCreditoStrategy;
import com.well.banco_digital_CDBW.strategy.PagamentoCartaoDebitoStrategy;

@Service
public class PagamentoService {
	
	private final Map<String, PagamentoStrategy> mapStrategy;
	
	public PagamentoService(PagamentoCartaoCreditoStrategy creditoStrategy,
			PagamentoCartaoDebitoStrategy debitoStrategy) {	
		this.mapStrategy = Map.of(
			"CARTAODECREDITO", creditoStrategy,
			"CARTAODEDEBITO", debitoStrategy
			);
	}

	public PagamentoResDto pagar(Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		PagamentoStrategy formaPagamento = mapStrategy.get(pagamentoReq
				.tipoPagamento()
				.toUpperCase()
				.replace(" ", "")
				.replace("É", "E")
		);
		if(formaPagamento == null) { throw new FormaDePagamentoInvalidaException();}
		return formaPagamento.pagar(clienteLogado, pagamentoReq);

	}

}
