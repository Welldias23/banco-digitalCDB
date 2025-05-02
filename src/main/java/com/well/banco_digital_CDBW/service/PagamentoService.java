package com.well.banco_digital_CDBW.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.strategy.PagamentoCartaoCreditoStrategy;
import com.well.banco_digital_CDBW.strategy.PagamentoCartaoDebitoStrategy;

@Service
public class PagamentoService {
	
	private final Map<String, PagamentoStrategy> mapStrategy = Map.of(
			"Cartao de Credito", new PagamentoCartaoCreditoStrategy(),
			"Cartao de Debito", new PagamentoCartaoDebitoStrategy()
	);
	

	public PagamentoResDto pagar(Long idCartao, Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		
		return mapStrategy.get(pagamentoReq.tipoPagamento()).pagar(idCartao, clienteLogado, pagamentoReq);

	}

}
