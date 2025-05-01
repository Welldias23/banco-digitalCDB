package com.well.banco_digital_CDBW.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Pagamento;
import com.well.banco_digital_CDBW.entity.PagamentoCredito;
import com.well.banco_digital_CDBW.entity.PagamentoDebito;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.repository.PagamentoRepository;

@Service
public class PagamentoService {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public PagamentoResDto pagar(Long idCartao, Cliente clienteLogado, PagamentoReqDto pagamentoReq) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = cartaoService.buscarPorIdECliente(idCartao, clienteLogado);
		var pagamento = tipoPagamento(cartao, pagamentoReq);
		pagamentoRepository.save(pagamento);

		return new PagamentoResDto(pagamento);
	}

	private Pagamento tipoPagamento(Cartao cartao, PagamentoReqDto pagamentoReq) {
		cartao = (Cartao) Hibernate.unproxy(cartao);
		//refatorar para o pafrao strategy
		if(cartao.getClass() == CartaoCredito.class) {
			cartaoService.limiteESuficiente((CartaoCredito) cartao, pagamentoReq.valor());
			var pagamento =  new PagamentoCredito((CartaoCredito) cartao, pagamentoReq);
			pagamento.pagar();
			cartaoService.atualizarCartao(cartao);
			return pagamento;
		}else if(cartao.getClass() == CartaoDebito.class) {
			cartaoService.limiteDiarioSuficiente((CartaoDebito) cartao, pagamentoReq.valor());
			var conta = contaService.buscarContaPorId(cartao.getConta().getId());
			contaService.validarSaldoSufuciente(conta.getSaldo(), pagamentoReq.valor());
			var pagamento =  new PagamentoDebito((CartaoDebito) cartao, pagamentoReq);
			pagamento.pagar();
			((CartaoDebito) cartao).diminuirLimiteDiario(pagamento.getValor());
			contaService.atualizarSaldoConta(conta);
			return pagamento;			
		} 
		
		throw new CartaoNaoExisteException();
	}

}
