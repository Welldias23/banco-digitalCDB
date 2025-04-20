package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.CartaoReqDto;
import com.well.banco_digital_CDBW.dto.CartaoResDto;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.repository.CartaoRepository;
import com.well.banco_digital_CDBW.utils.CriarNumeroCartao;

@Service
public class CartaoService {
	
	private static final Cartao CartaoDebito = null;

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CriarNumeroCartao geraNumero; 

	public CartaoResDto criar(Cliente cliente, Long idConta, CartaoReqDto cartaoACriar) {
		var conta = contaService.buscarPorIdContaIdCliente(idConta, cliente.getId());
		var numeroCartao = geraNumero.criarNumeroCartao(cartaoACriar.bandeira());
		var cartao = new CartaoCredito(conta, cliente, cartaoACriar.senha(), numeroCartao); 
		cartaoRepository.save(cartao);
		return new CartaoResDto(cartao);
	}

	public void criar(Conta conta) {
		var numeroCartao = geraNumero.criarNumeroCartao("masterCard");
		var cartao = new CartaoDebito(conta, numeroCartao); 
		cartaoRepository.save(cartao);
	}

	public CartaoResDto detalhar(Long idCartao, Cliente clienteLogado) {
		clienteService.clienteId(clienteLogado.getId());	
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);		
		var tipoCartao = tipoDoCartao(cartao);
		return tipoCartao;
	}


	private void cartaoPertenceCliente(Cartao cartao, Cliente clienteLogado) {
		var conta = contaService.buscarPorIdContaIdCliente(cartao.getConta().getId(), clienteLogado.getId());
		if (conta == null) {
			throw new CartaoNaoExisteException();
		}
	}

	private Cartao buscarPorId(Long idCartao) {
		var cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new CartaoNaoExisteException());
		return cartao;
	}

	private CartaoResDto tipoDoCartao(Cartao cartao) {
		Cartao cartaoDesproxiado = (Cartao) Hibernate.unproxy(cartao);
		if(cartaoDesproxiado.getClass() == CartaoDebito.class) {
			var cartaoDebito = (CartaoDebito) cartaoDesproxiado;
			return new CartaoResDto(cartaoDebito);
		}
		var cartaoCredito = (CartaoCredito) cartaoDesproxiado;
		return new CartaoResDto(cartaoCredito); 		
	}


	public CartaoResDto alterarLimiteCredito(Long idCartao, Cliente clienteLogado, BigDecimal limite) {
		clienteService.clienteId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		isCartaoCredito(cartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		((CartaoCredito) cartao).alterarLimite(limite);
		var cc = (CartaoCredito) cartao;
		cartaoRepository.save(cc);
		return new CartaoResDto(cc);
	}



	public Cartao alterarStatus(Long idCartao) {
		var cartao = buscarPorId(idCartao);
		cartao.mudarStatus();
		
		return cartao;
	}

	public Cartao alterarSenha(Long idCartao, String senha) {
		var cartao = buscarPorId(idCartao);
		cartao.mudarSenha(senha);
		
		return cartao;
	}

	public CartaoDebito alterarLimiteDiario(Long idCartao, BigDecimal novoLimite) {
		var cartao = buscarPorId(idCartao);
		isCartaoDebito(cartao);
		((CartaoDebito) cartao).alterarLimiteDiario(novoLimite);
		
		return (CartaoDebito) cartao;
	}

	private void isCartaoDebito(Cartao cartao) {
		if(cartao.getClass() != CartaoDebito.class) {
			throw new CartaoNaoExisteException();
		}
		
	}
	
	private void isCartaoCredito(Cartao cartao) {
		if(cartao.getClass() != CartaoCredito.class) {
			throw new CartaoNaoExisteException();
		}
		
	}


}
