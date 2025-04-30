package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.exception.LimiteDiarioInsuficiente;
import com.well.banco_digital_CDBW.exception.TipoCartaoInvalidoException;
import com.well.banco_digital_CDBW.repository.CartaoRepository;
import com.well.banco_digital_CDBW.utils.CriarNumeroCartao;




@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final ClienteService clienteService;

    public CartaoService(CartaoRepository cartaoRepository,
                         ClienteService clienteService, 
                         CriarNumeroCartao geraNumero) {
        this.cartaoRepository = cartaoRepository;
        this.clienteService = clienteService;
    }
    
    

	public CartaoDto detalharCartao(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());	
		Cartao cartao = buscarPorIdECliente(idCartao, clienteLogado);
		CartaoDto cartaoTipo = verificarTipoDoCartao(cartao);
		
		return cartaoTipo;
	}


	public CartaoDto ativarStatusCartao(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		Cartao cartao = buscarPorIdECliente(idCartao, clienteLogado);
		cartao.ativarStatus();
		cartaoRepository.save(cartao);
		
		return new CartaoDto(cartao);
	}
	
	public CartaoDto desativarStatusCartao(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		Cartao cartao = buscarPorIdECliente(idCartao, clienteLogado);
		cartao.desativarStatus();
		cartaoRepository.save(cartao);		
		
		return new CartaoDto(cartao);
	}	

	public CartaoDto alterarSenhaCartao(Long idCartao, Cliente clienteLogado, String senha) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		Cartao cartao = buscarPorIdECliente(idCartao, clienteLogado);
		cartao.mudarSenha(senha);
		cartaoRepository.save(cartao);
		
		return new CartaoDto(cartao);
	}

	public Cartao buscarPorIdECliente(Long idCartao, Cliente clienteLogado) {
		return cartaoRepository.findByIdAndContaClienteId(idCartao)
				.orElseThrow(() -> new CartaoNaoExisteException());
	}

	private CartaoDto verificarTipoDoCartao(Cartao cartao) {
		//desacolplar esse codigo
		cartao = (Cartao) Hibernate.unproxy(cartao);
		//
		if(cartao instanceof CartaoDebito cartaoDebito) {
			return new CartaoDto(cartaoDebito);
		}else if(cartao instanceof CartaoCredito cartaoCredito) {
			return new CartaoDto(cartaoCredito);
		}
		throw new TipoCartaoInvalidoException(); 		
	}


	public void atualizarCartao(Cartao cartao) {
		cartaoRepository.save(cartao);
	}

	public void limiteDiarioSuficiente(CartaoDebito cartao, BigDecimal valor) {
		if (cartao.getLimiteDiario().compareTo(valor) > 0) {
			throw new LimiteDiarioInsuficiente();
		}
		
	}

}
