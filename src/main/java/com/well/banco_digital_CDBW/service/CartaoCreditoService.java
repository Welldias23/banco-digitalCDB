package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.util.List;

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
import com.well.banco_digital_CDBW.exception.ContaJaPossuiCartaoDesseTipo;
import com.well.banco_digital_CDBW.exception.LimiteDeCreditoInsuficiente;
import com.well.banco_digital_CDBW.exception.ValorMaiorQueFatura;
import com.well.banco_digital_CDBW.repository.CartaoCreditoRepository;
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
		validarSeJaTemCartaoCredito(conta);
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
		
		return new FaturaDto(cartao.getLimiteCreditoUsado(), cartao.getFatura());
	}

	public FaturaPaga pagarFatura(Long idCartao, Cliente clienteLogado, PagamentoFatura pagamentoFatura) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoCredito cartao = buscarCartaoCreditoPorIdECliente(idCartao, clienteLogado);
		valorDePagamentoMaiorValorFatura(cartao, pagamentoFatura.valor());
		cartao.creditarNoLimite(pagamentoFatura.valor());
		cartaoRepository.save(cartao);
		
		return new FaturaPaga(cartao.getLimiteCreditoUsado(), pagamentoFatura.valor(), cartao.getLimiteCreditoUsado());
	}
	
	
	public CartaoCredito buscarCartaoCreditoPorIdECliente(Long idCartao, Cliente clienteLogado) {
		return cartaoRepository.findByIdAndContaClienteId(idCartao, clienteLogado.getId())
				.orElseThrow(() -> new CartaoNaoExisteException());
	}

	
	private void valorDePagamentoMaiorValorFatura(CartaoCredito cartao, BigDecimal valor) {
		if(valor.compareTo(cartao.getLimiteCreditoUsado()) > 0) {
			throw new ValorMaiorQueFatura();
		}
	}

	
	public void limiteESuficiente(CartaoCredito cartao, BigDecimal valor) {
		BigDecimal limiteDisponivel =  cartao.getLimiteCreditoTotal()
				.subtract(cartao.getLimiteCreditoUsado());		
		if (limiteDisponivel.compareTo(valor) < 0) {
			throw new LimiteDeCreditoInsuficiente();
		}
	}
	
	private void validarSeJaTemCartaoCredito(Conta conta) {
		List<Cartao> contas = cartaoRepository.findByContaId(conta.getId());
		contas.stream()
			.filter(cartao -> cartao instanceof CartaoCredito)
			.findFirst()
			.ifPresent(cartao -> {
				throw new ContaJaPossuiCartaoDesseTipo();
			});
	}




}
