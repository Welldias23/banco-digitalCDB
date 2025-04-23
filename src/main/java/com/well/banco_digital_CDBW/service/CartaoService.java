package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.CartaoReqDto;
import com.well.banco_digital_CDBW.dto.CartaoResDto;
import com.well.banco_digital_CDBW.dto.FaturaDto;
import com.well.banco_digital_CDBW.dto.FaturaPaga;
import com.well.banco_digital_CDBW.dto.PagamentoFatura;
import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.exception.CartaoNaoExisteException;
import com.well.banco_digital_CDBW.exception.LimiteDeCreditoInsuficiente;
import com.well.banco_digital_CDBW.exception.LimiteDiarioInsuficiente;
import com.well.banco_digital_CDBW.exception.TipoCartaoInvalidoException;
import com.well.banco_digital_CDBW.exception.ValorMaiorQueFatura;
import com.well.banco_digital_CDBW.repository.CartaoRepository;
import com.well.banco_digital_CDBW.utils.CriarNumeroCartao;

import jakarta.validation.constraints.NotNull;



@Service
public class CartaoService {

    private final ContaService contaService;
    private final CartaoRepository cartaoRepository;
    private final ClienteService clienteService;
    private final CriarNumeroCartao geraNumero;

    public CartaoService(ContaService contaService, CartaoRepository cartaoRepository,
                         ClienteService clienteService, CriarNumeroCartao geraNumero) {
        this.contaService = contaService;
        this.cartaoRepository = cartaoRepository;
        this.clienteService = clienteService;
        this.geraNumero = geraNumero;
    }

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
		clienteService.buscarclientePorId(clienteLogado.getId());	
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);		
		var tipoCartao = verificarTipoDoCartao(cartao);
		return tipoCartao;
	}
	
	public CartaoResDto alterarLimiteCredito(Long idCartao, Cliente clienteLogado, BigDecimal limite) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		isCartaoCredito(cartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		((CartaoCredito) cartao).alterarLimite(limite);
		var cc = (CartaoCredito) cartao;
		cartaoRepository.save(cc);
		return new CartaoResDto(cc);
	}


	public Cartao ativarStatus(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		cartao.ativarStatus();
		cartaoRepository.save(cartao);
		return cartao;
	}
	
	public Cartao desativarStatus(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		cartao.desativarStatus();
		cartaoRepository.save(cartao);		
		return cartao;
	}	

	public Cartao alterarSenha(Long idCartao, Cliente clienteLogado, String senha) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		cartao.mudarSenha(senha);
		cartaoRepository.save(cartao);
		return cartao;
	}

	public CartaoDebito alterarLimiteDiario(Long idCartao, Cliente clienteLogado, BigDecimal novoLimite) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao = buscarPorId(idCartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		isCartaoDebito(cartao);
		((CartaoDebito) cartao).alterarLimiteDiario(novoLimite);
		
		return (CartaoDebito) cartao;
	}

	public FaturaDto consultarFatura(Long idCartao, Cliente clienteLogado) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao =(CartaoCredito)  buscarPorId(idCartao);
		isCartaoCredito(cartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		var valorFatura =cartao.calcularFatura();
		return new FaturaDto(valorFatura, cartao.getFatura());
	}

	public FaturaPaga pagarFatura(Long idCartao, Cliente clienteLogado, PagamentoFatura pagamentoFatura) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var cartao =(CartaoCredito) buscarPorId(idCartao);
		isCartaoCredito(cartao);
		cartaoPertenceCliente(cartao, clienteLogado);
		var valorFatura =cartao.calcularFatura();
		valorMaiorFatura(cartao, pagamentoFatura.valor());
		cartao.creditarNoLimite(pagamentoFatura.valor());
		cartaoRepository.save(cartao);
		return new FaturaPaga(valorFatura, pagamentoFatura.valor(), cartao.getLimiteCreditoDisponivel());
	}


	private void valorMaiorFatura(CartaoCredito cartao, BigDecimal valor) {
		var valorFatura = cartao.calcularFatura();
		if(valor.compareTo(valorFatura) > 0) {
			throw new ValorMaiorQueFatura();
		}
	}

	public void cartaoPertenceCliente(Cartao cartao, Cliente clienteLogado) {
		var conta = contaService.buscarPorIdContaIdCliente(cartao.getConta().getId(), clienteLogado.getId());
		if (conta == null) {
			throw new CartaoNaoExisteException();
		}
	}

	public Cartao buscarPorId(Long idCartao) {
		var cartao = cartaoRepository.findById(idCartao).orElseThrow(() -> new CartaoNaoExisteException());
		return cartao;
	}

	private CartaoResDto verificarTipoDoCartao(Cartao cartao) {
		cartao = (Cartao) Hibernate.unproxy(cartao);
		if(cartao instanceof CartaoDebito cc) {
			//var cartaoDebito = (CartaoDebito) cartao;
			return new CartaoResDto(cc);
		}
		var cartaoCredito = (CartaoCredito) cartao;
		return new CartaoResDto(cartaoCredito); 		
	}


	private void isCartaoDebito(Cartao cartao) {
		if(cartao.getClass() != CartaoDebito.class) {
			throw new TipoCartaoInvalidoException();
		}
		
	}
	
	private void isCartaoCredito(Cartao cartao) {
		if(cartao.getClass() != CartaoCredito.class) {
			throw new TipoCartaoInvalidoException();
		}
		
	}

	public void limiteESuficiente(CartaoCredito cartao, BigDecimal valor) {
		var resto = cartao.getLimiteCreditoDisponivel().subtract(valor);
		if (resto.compareTo(BigDecimal.ZERO) <= 0) {
			throw new LimiteDeCreditoInsuficiente();
		}
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
