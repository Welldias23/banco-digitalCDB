package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.entity.CategoriaCliente;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.exception.ContaNaoExisteException;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.exception.SaldoInsuficienteException;
import com.well.banco_digital_CDBW.repository.ContaRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class ContaService {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaRepository contaRepository;
	
	public Conta criarConta(Long id, ContaReqDto contaAbrir) {
		var cliente = clienteService.clienteId(id);
		var conta = criar(cliente, contaAbrir);
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
	
	public Transferencia transferir(Cliente clienteOrigem, TransferenciaReqDto transferenciaAFazer) {
		var contaOrigem = contaRepository.findByIdAndClienteId(transferenciaAFazer.idContaOrigem(), clienteOrigem.getId());
		contaExiste(contaOrigem);
		temSaldo(contaOrigem.getSaldo(), transferenciaAFazer.valor());
		var contaDestino = contaRepository.findById(transferenciaAFazer.idContaDestino());
		contaExiste(contaDestino);
		var clienteDestino = clienteService.clienteId(contaDestino.get().getCliente().getId());
		var transferencia = new Transferencia(contaOrigem, contaDestino.get(), transferenciaAFazer.valor());
		//rever esse detalhe
		transferencia.setNomeOrigem(clienteOrigem.getNome());
		transferencia.setNomeDestino(clienteDestino.getNome());
		
		return transferencia;
	}

	private void temSaldo(BigDecimal saldo,BigDecimal valor) {
		var valorResto = saldo.subtract(valor);
		if(valorResto.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException();
		}
	}

	private void contaExiste(Optional<Conta> contaDestino) {
		if(contaDestino.get() == null) {
			throw new ContaNaoExisteException();
		}
	}

	private void contaExiste(Conta contaOrigem) {
		if(contaOrigem == null) {
			throw new ContaNaoExisteException();
		}		
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
