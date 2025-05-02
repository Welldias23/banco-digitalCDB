package com.well.banco_digital_CDBW.strategy;


import org.springframework.beans.factory.annotation.Autowired;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.PagamentoCredito;
import com.well.banco_digital_CDBW.repository.PagamentoRepository;
import com.well.banco_digital_CDBW.service.CartaoCreditoService;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.PagamentoStrategy;

public class PagamentoCartaoCreditoStrategy implements PagamentoStrategy{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
		

	@Override
	public PagamentoResDto pagar(Long idCartao, Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = cartaoCreditoService.buscarCartaoCreditoPorIdECliente(idCartao, clienteLogado);
		PagamentoCredito pagamento = new PagamentoCredito(cartao, pagamentoReq);
		pagamentoRepository.save(pagamento);

		return new PagamentoResDto(pagamento);
	}

}
