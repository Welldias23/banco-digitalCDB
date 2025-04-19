package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaReqDto;
import com.well.banco_digital_CDBW.dto.DepositoReqDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.TransacaoDto;
import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.entity.CartaoDebito;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.entity.Deposito;
import com.well.banco_digital_CDBW.entity.TaxaManuntencao;
import com.well.banco_digital_CDBW.entity.Transacao;
import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.entity.TransferenciaPix;
import com.well.banco_digital_CDBW.exception.ChavePixJaExisteException;
import com.well.banco_digital_CDBW.exception.ChavePixNaoExisteException;
import com.well.banco_digital_CDBW.exception.ContaNaoExisteException;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.exception.SaldoInsuficienteException;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class ContaService {
	
	@Autowired
	private ClienteService clienteService;
	

	@Autowired
	private ContaRepository contaRepository;
	
	
	public Conta criar(Long id, ContaReqDto contaAbrir) {
		var cliente = clienteService.clienteId(id);
		var conta = tipar(cliente, contaAbrir);
		conta.gerarNumeroConta(id);
		contaRepository.save(conta);

		return conta;
	}
	
	public Conta cadastrarPix(Cliente clienteLogado, PixDto pix) {
		clienteService.clienteId(clienteLogado.getId());
		pixUnico(pix.chavePix());
		var conta = contaRepository.findByIdAndClienteId(pix.contaId(), clienteLogado.getId());
		temConta(conta);
		conta.setChavePix(pix.chavePix());
		contaRepository.save(conta);
		
		return conta;
	}


	public List<Conta> buscarTodas(Long id) {
		var cliente = clienteService.clienteId(id);
		var contas = cliente.getContas();
		return contas;
	}
	


	public Conta buscarPorPix(String chavePix) {
		var conta = contaRepository.getReferenceByChavePix(chavePix);
		if(conta == null) {
			throw new ChavePixNaoExisteException();
		}
		return conta;
	}
	
	
	private void pixUnico(String chavePix) {
		var conta = contaRepository.findByChavePix(chavePix);
		if(conta != null) {
			throw new ChavePixJaExisteException();
		}		
	}


	public void temSaldo(BigDecimal saldo,BigDecimal valor) {
		var valorResto = saldo.subtract(valor);
		if(valorResto.compareTo(BigDecimal.ZERO) < 0) {
			throw new SaldoInsuficienteException();
		}
	}

	private void temConta(Conta conta) {
		if(conta == null) {
			throw new ContaNaoExisteException();
		}		
	}
	

	public Conta buscarPorId(Long idContaDestino) {
		var conta = contaRepository.getReferenceById(idContaDestino);
		temConta(conta);
		return conta;
	}

	private Conta tipar(Cliente cliente, ContaReqDto contaAbrir) {
		if(contaAbrir.tipoConta().toUpperCase().equals("CONTA CORRENTE")) {
			var conta = new ContaCorrente(cliente);
			
			return conta;
		}else if(contaAbrir.tipoConta().toUpperCase().equals("CONTA POUPANÇA")){
			var conta = new ContaPoupanca(cliente);
			conta.setRendimento(0.5);
			
			return conta;
		}else {
			throw new CriarContaException();
		}
	}


	public List<Conta> buscarPorData(int diaDoMes) {
		var contas = contaRepository.findAllByDayMonth(diaDoMes);
		return contas;
	}

	public Conta buscarPorIdContaIdCliente(Long idConta, Long idCliente) {
		var conta = contaRepository.findByIdAndClienteId(idConta, idCliente);
		temConta(conta);	
		return conta;
	}

	public BigDecimal buscarSaldo(Long idConta, Long idCliente) {
		var conta = contaRepository.findByIdAndClienteId(idConta, idCliente);
		temConta(conta);
		var saldo = conta.getSaldo();
		
		return saldo;
	}

	
	

}
