package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.repository.CartaoRepository;

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

}
