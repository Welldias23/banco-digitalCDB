package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.repository.CartaoDebitoRepository;
import com.well.banco_digital_CDBW.utils.CriarNumeroCartao;

@Service
public class CartaoDebitoService {
	
	private final ClienteService clienteService;
	
	private final CartaoDebitoRepository cartaoDebitoRepository; 
	
    private final CriarNumeroCartao geraNumero;
	
	public CartaoDebitoService(ClienteService clienteService,
							   CartaoDebitoRepository cartaoDebitoRepository, 
							   CriarNumeroCartao geraNumero) {
		this.clienteService = clienteService;
		this.cartaoDebitoRepository = cartaoDebitoRepository;
        this.geraNumero = geraNumero;
	}

	public CartaoDto alterarLimiteDiario(Long idCartao, Cliente clienteLogado, BigDecimal novoLimite) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		CartaoDebito cartao = buscarPorIdECliente(idCartao, clienteLogado);
		cartao.alterarLimiteDiario(novoLimite);
		cartaoDebitoRepository.save(cartao);
		
		return new CartaoDto(cartao);
	}
	
	//implementar a criçao do cartao de debito no ato da criaçao da conta
	public void criarCartaoDebito(Conta conta) {
		String numeroCartao = geraNumero.criarNumeroCartao("masterCard");
		CartaoDebito cartao = new CartaoDebito(conta, numeroCartao); 
		
		cartaoDebitoRepository.save(cartao);
	}

	

	public CartaoDebito buscarPorIdECliente(Long idCartao, Cliente clienteLogado) {
		return cartaoDebitoRepository.findByIdAndContaClienteId(idCartao, clienteLogado.getId())
				.orElseThrow(() -> new CartaoNaoExisteException());
	}

	
}
