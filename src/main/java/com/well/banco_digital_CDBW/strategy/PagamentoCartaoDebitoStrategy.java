package com.well.banco_digital_CDBW.strategy;

import org.springframework.stereotype.Component;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.PagamentoDebito;
import com.well.banco_digital_CDBW.repository.PagamentoRepository;
import com.well.banco_digital_CDBW.service.CartaoDebitoService;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.ContaService;
import com.well.banco_digital_CDBW.service.PagamentoStrategy;

@Component
public class PagamentoCartaoDebitoStrategy implements PagamentoStrategy{
	
	private final ClienteService clienteService;
	
	private final CartaoDebitoService cartaoDebitoService;
	
	private final ContaService contaService;
	
	private final PagamentoRepository pagamentoRepository;
	
	public PagamentoCartaoDebitoStrategy(ClienteService clienteService,CartaoDebitoService cartaoDebitoService,
			ContaService contaService, PagamentoRepository pagamentoRepository) {
		this.clienteService = clienteService;
		this.cartaoDebitoService = cartaoDebitoService;
		this.contaService = contaService;
		this.pagamentoRepository = pagamentoRepository;
	}
	

	@Override
	public PagamentoResDto pagar(Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoDebito cartao = cartaoDebitoService.buscarPorIdECliente(pagamentoReq.idDaFormaDePagamento(), clienteLogado);
		contaService.debitarSaldoConta(cartao.getConta(), pagamentoReq.valor());
		PagamentoDebito pagamento = new PagamentoDebito(cartao, pagamentoReq);
		pagamentoRepository.save(pagamento);

		return new PagamentoResDto(pagamento);
	}

}
