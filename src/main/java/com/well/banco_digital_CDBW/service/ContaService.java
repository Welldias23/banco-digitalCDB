package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaReqDto;
import com.well.banco_digital_CDBW.dto.DepositoReqDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.TransacaoDto;
import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.entity.CategoriaCliente;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.entity.Deposito;
import com.well.banco_digital_CDBW.entity.Transacao;
import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.entity.TransferenciaPix;
import com.well.banco_digital_CDBW.exception.ChavePixNaoExisteException;
import com.well.banco_digital_CDBW.exception.ContaNaoExisteException;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.exception.SaldoInsuficienteException;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ContaService {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaRepository contaRepository;
	
	//DESACOPLAR
	@Autowired
	public TransacaoRepository transacaoRepository;
	
	public Conta criarConta(Long id, ContaReqDto contaAbrir) {
		var cliente = clienteService.clienteId(id);
		var conta = criar(cliente, contaAbrir);
		contaRepository.save(conta);

		return conta;
	}
	
	public Conta cadastrarPix(Cliente clienteLogado, PixDto pix) {
		clienteService.clienteId(clienteLogado.getId());
		var conta = contaRepository.findByIdAndClienteId(pix.contaId(), clienteLogado.getId());
		temConta(conta);
		conta.setChavePix(pix.chavePix());
		contaRepository.save(conta);
		
		return conta;
	}
	
	public Conta buscarUma(Long idConta, Long id) {
		clienteService.clienteId(id);
		var conta = contaRepository.findByIdAndClienteId(idConta, id);
		return conta;
	}

	public List<Conta> buscarTodas(Long id) {
		var cliente = clienteService.clienteId(id);
		var contas = cliente.getContas();
		return contas;
	}
	
	//DESACOPLAR
	public Transferencia transferir(Cliente clienteOrigem, TransferenciaReqDto transferenciaAFazer) {
		var transacao = iniciarTransacao(clienteOrigem, transferenciaAFazer);
		var transferencia = new Transferencia(transacao.contaOrigem(), transacao.contaDestino(), transferenciaAFazer.valor());
		//rever esse detalhe
		transferencia.setNomeOrigem(transacao.nomeOrigem());
		transferencia.setNomeDestino(transacao.nomeDestino());
		contaRepository.save(transacao.contaDestino());
		contaRepository.save(transacao.contaOrigem());
		transacaoRepository.save(transferencia);
		
		return transferencia;
	}
	
	public TransferenciaPix transferirPix(Cliente clienteOrigen, TransferenciaPixReqDto transferenciaPixAFazer) {
		var contaDestino = pixExiste(transferenciaPixAFazer.chavePix());
		var transacao = iniciarTransacao(clienteOrigen, new TransferenciaReqDto(transferenciaPixAFazer.idContaOrigem(), contaDestino.getId(), transferenciaPixAFazer.valor()));
		var transferenciaPix = new TransferenciaPix(transacao.contaOrigem(), transacao.contaDestino(), transferenciaPixAFazer.valor());
		contaRepository.save(transacao.contaDestino());
		contaRepository.save(transacao.contaOrigem());
		transacaoRepository.save(transferenciaPix);
		
		return transferenciaPix;
	}
	
	private Conta pixExiste(String chavePix) {
		var conta = contaRepository.getReferenceByChavePix(chavePix);
		if(conta == null) {
			throw new ChavePixNaoExisteException();
		}
		return conta;
	}

	private TransacaoDto iniciarTransacao(Cliente clienteOrigem, TransferenciaReqDto transferenciaAFazer) {
		clienteService.clienteId(clienteOrigem.getId());
		var contaOrigem = contaRepository.findByIdAndClienteId(transferenciaAFazer.idContaOrigem(), clienteOrigem.getId());
		temConta(contaOrigem);
		temSaldo(contaOrigem.getSaldo(), transferenciaAFazer.valor());
		var contaDestino = contaRepository.getReferenceById(transferenciaAFazer.idContaDestino());
		temConta(contaDestino);
		var clienteDestino = clienteService.clienteId(contaDestino.getCliente().getId());
		var transacao = new TransacaoDto(contaOrigem, clienteOrigem.getNome(), contaDestino, clienteDestino.getNome());
		
		return transacao;
	}

	public Deposito depositar(Cliente clienteLogado, DepositoReqDto deposito) {
		clienteService.clienteId(clienteLogado.getId());
		var contaDestino = contaExiste(deposito.idContaDestino());
		var clienteDestino = clienteService.clienteId(contaDestino.getCliente().getId());
		var depositoEfetuado = new Deposito(contaDestino, deposito.valor());
		depositoEfetuado.setNomeDestino(clienteDestino.getNome());
		System.out.println(contaDestino.getSaldo());
		contaRepository.save(contaDestino);
		transacaoRepository.save(depositoEfetuado);
		
		return depositoEfetuado;
	}


	private void temSaldo(BigDecimal saldo,BigDecimal valor) {
		var valorResto = saldo.subtract(valor);
		if(valorResto.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException();
		}
	}

	private void temConta(Conta contaOrigem) {
		if(contaOrigem == null) {
			throw new ContaNaoExisteException();
		}		
	}
	

	private Conta contaExiste(Long idContaDestino) {
		var conta = contaRepository.getReferenceById(idContaDestino);
		if(conta == null) {
			throw new ContaNaoExisteException();
		}	
		return conta;
	}

	private Conta criar(Cliente cliente, ContaReqDto contaAbrir) {
		if(contaAbrir.tipoConta().toUpperCase().equals("CONTA CORRENTE")) {
			var conta = new ContaCorrente(cliente);
			atribuirTaxaManutencao(cliente, conta);
			
			return conta;
		}else if(contaAbrir.tipoConta().toUpperCase().equals("CONTA POUPANÇA")){
			var conta = new ContaPoupanca(cliente);
			conta.setRendimento(0.5);
			
			return conta;
		}else {
			throw new CriarContaException();
		}
	}

	private void atribuirTaxaManutencao(Cliente cliente, ContaCorrente conta) {
		if(cliente.getCategoria().equals(CategoriaCliente.COMUM)) {
			conta.setTaxaManutencao(30.0);
		} else if(cliente.getCategoria().equals(CategoriaCliente.PREMIU)) {
			conta.setTaxaManutencao(15.0);
		} else if(cliente.getCategoria().equals(CategoriaCliente.SUPER)) {
			conta.setTaxaManutencao(1.0);
		} else {
			throw new CriarContaException();
		}
		
	}






	
	
	

}
