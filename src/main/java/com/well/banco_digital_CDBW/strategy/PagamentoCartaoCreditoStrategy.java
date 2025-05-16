package com.well.banco_digital_CDBW.strategy;

import org.springframework.stereotype.Component;

import com.well.banco_digital_CDBW.dto.PagamentoDto;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.PagamentoCredito;
import com.well.banco_digital_CDBW.mapper.PagamentoMapper;
import com.well.banco_digital_CDBW.repository.PagamentoRepository;
import com.well.banco_digital_CDBW.service.CartaoCreditoService;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.PagamentoStrategy;

@Component
public class PagamentoCartaoCreditoStrategy implements PagamentoStrategy{
	
	private final ClienteService clienteService;	
	private final CartaoCreditoService cartaoCreditoService;	
	private final PagamentoRepository pagamentoRepository;
	private final PagamentoMapper mapper;
	
	public PagamentoCartaoCreditoStrategy(ClienteService clienteService,CartaoCreditoService cartaoCreditoService,
			PagamentoRepository pagamentoRepository, PagamentoMapper mapper) {
		this.clienteService = clienteService;
		this.cartaoCreditoService = cartaoCreditoService;
		this.pagamentoRepository = pagamentoRepository;
		this.mapper = mapper;
	}

	@Override
	public PagamentoDto pagar(Cliente clienteLogado, PagamentoDto pagamentoReq) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = cartaoCreditoService.buscarCartaoCreditoPorIdECliente(pagamentoReq.idDoCartao(), clienteLogado);
		PagamentoCredito pagamento = new PagamentoCredito(cartao, pagamentoReq);
		cartao.debitarNoLimite(pagamento.getValor());
		pagamentoRepository.save(pagamento);

		return mapper.toPagamentoDto(pagamento);
	}

}
