package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.dto.FaturaDto;
import com.well.banco_digital_CDBW.dto.FaturaPaga;
import com.well.banco_digital_CDBW.dto.PagamentoFatura;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.exception.LimiteDeCreditoInsuficiente;
import com.well.banco_digital_CDBW.exception.TipoCartaoInvalidoException;
import com.well.banco_digital_CDBW.exception.ValorMaiorQueFatura;
import com.well.banco_digital_CDBW.repository.CartaoCreditoRepository;
import com.well.banco_digital_CDBW.repository.CartaoRepository;
import com.well.banco_digital_CDBW.utils.CriarNumeroCartao;


@Service
public class CartaoCreditoService {
	
	private final ContaService contaService;
	
	private final ClienteService clienteService;
	
	private final CriarNumeroCartao geraNumero;
	
	private final CartaoCreditoRepository cartaoRepository;
	
	public CartaoCreditoService(ContaService contaService,
			             ClienteService clienteService,
						 CriarNumeroCartao geraNumero,
						 CartaoCreditoRepository cartaoRepository) {
		this.contaService = contaService;
		this.clienteService = clienteService;
		this.geraNumero = geraNumero;
		this.cartaoRepository = cartaoRepository;
	}
	
	public CartaoDto criarCartaoCredito(Cliente cliente, Long idConta, CartaoDto cartaoACriar) {
		Conta conta = contaService.buscarContaPorIdContaIdCliente(idConta, cliente.getId());
		String numeroCartao = geraNumero.criarNumeroCartao(cartaoACriar.bandeira());
		CartaoCredito cartao = new CartaoCredito(
				conta, 
				cartaoACriar.senha(), 
				numeroCartao
		); 
		cartao.calcularLimiteInicial(cliente);
		cartaoRepository.save(cartao);
		
		return new CartaoDto(cartao);
	}
	
	
	public CartaoDto alterarLimiteCredito(Long idCartao, Cliente clienteLogado, BigDecimal limite) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = buscarCartaoCreditoPorIdECliente(idCartao, clienteLogado);
		cartao.alterarLimite(limite);
		cartaoRepository.save(cartao);
		
		return new CartaoDto(cartao);
	}
	

	public FaturaDto consultarFatura(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = buscarCartaoCreditoPorIdECliente(idCartao, clienteLogado);
		BigDecimal valorFatura =cartao.calcularFatura();
		
		return new FaturaDto(valorFatura, cartao.getFatura());
	}

	public FaturaPaga pagarFatura(Long idCartao, Cliente clienteLogado, PagamentoFatura pagamentoFatura) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = buscarCartaoCreditoPorIdECliente(idCartao, clienteLogado);
		BigDecimal valorFatura =cartao.calcularFatura();
		valorDePagamentoMaiorValorFatura(cartao, pagamentoFatura.valor());
		cartao.creditarNoLimite(pagamentoFatura.valor());
		cartaoRepository.save(cartao);
		
		return new FaturaPaga(valorFatura, pagamentoFatura.valor(), cartao.getLimiteCreditoDisponivel());
	}
	
	
	private CartaoCredito buscarCartaoCreditoPorIdECliente(Long idCartao, Cliente clienteLogado) {
		return cartaoRepository.findByIdAndContaClienteId(idCartao, clienteLogado.getId())
				.orElseThrow(() -> new CartaoNaoExisteException());
	}

	
	private void valorDePagamentoMaiorValorFatura(CartaoCredito cartao, BigDecimal valor) {
		var valorFatura = cartao.calcularFatura();
		if(valor.compareTo(valorFatura) > 0) {
			throw new ValorMaiorQueFatura();
		}
	}

	
	public void limiteESuficiente(CartaoCredito cartao, BigDecimal valor) {
		var resto = cartao.getLimiteCreditoDisponivel().subtract(valor);
		if (resto.compareTo(BigDecimal.ZERO) <= 0) {
			throw new LimiteDeCreditoInsuficiente();
		}
	}



}
