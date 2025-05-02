package com.well.banco_digital_CDBW.strategy;


import org.springframework.beans.factory.annotation.Autowired;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.PagamentoDebito;
import com.well.banco_digital_CDBW.repository.PagamentoRepository;
import com.well.banco_digital_CDBW.service.CartaoDebitoService;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.PagamentoStrategy;

public class PagamentoCartaoDebitoStrategy implements PagamentoStrategy{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CartaoDebitoService cartaoDebitoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	

	@Override
	public PagamentoResDto pagar(Long idCartao, Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoDebito cartao = cartaoDebitoService.buscarPorIdECliente(idCartao, clienteLogado);
		PagamentoDebito pagamento = new PagamentoDebito(cartao, pagamentoReq);
		pagamentoRepository.save(pagamento);

		return new PagamentoResDto(pagamento);
	}

}
