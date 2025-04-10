package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.repository.CartaoRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class CartaoService {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private CartaoRepository cartaoRepository;

	public CartaoCredito criar(Cliente cliente, Long idConta) {
		var conta = contaService.buscarUma(idConta, cliente.getId());
		var cartao = new CartaoCredito(conta, cliente); 
		cartaoRepository.save(cartao);
		return cartao;
	}

	public void criar(Conta conta) {
		var cartao = new CartaoDebito(conta); 
		cartaoRepository.save(cartao);
	}

	public Cartao buscar(Long idCartao) {
		var cartao = cartaoRepository.getReferenceById(idCartao);
		temCartao(cartao);
		return cartao;
	}

	private void temCartao(Cartao cartao) {
		if(cartao == null) {
			throw new CartaoNaoExisteException();
		}
		
	}

	public CartaoCredito alterarLimiteCredito(Long idCartao, BigDecimal limite) {
		var cartao = cartaoRepository.getReferenceById(idCartao);
		temCartao(cartao);
		isCartaoCredito(cartao);
		((CartaoCredito) cartao).alterarLimite(limite);
		
		return (CartaoCredito) cartao;
	}

	private void isCartaoCredito(Cartao cartao) {
		if(cartao.getClass() != CartaoCredito.class) {
			throw new CartaoNaoExisteException();
		}
		
	}

	public Cartao alterarStatus(Long idCartao) {
		var cartao = cartaoRepository.getReferenceById(idCartao);
		temCartao(cartao);
		cartao.mudarStatus();
		
		return cartao;
	}

	public Cartao alterarSenha(Long idCartao, String senha) {
		var cartao = cartaoRepository.getReferenceById(idCartao);
		temCartao(cartao);
		cartao.mudarSenha(senha);
		
		return cartao;
	}

	public CartaoDebito alterarLimiteDiario(Long idCartao, BigDecimal novoLimite) {
		var cartao = cartaoRepository.getReferenceById(idCartao);
		temCartao(cartao);
		isCartaoDebito(cartao);
		((CartaoDebito) cartao).alterarLimiteDiario(novoLimite);
		
		return (CartaoDebito) cartao;
	}

	private void isCartaoDebito(Cartao cartao) {
		if(cartao.getClass() != CartaoDebito.class) {
			throw new CartaoNaoExisteException();
		}
		
	}


}
