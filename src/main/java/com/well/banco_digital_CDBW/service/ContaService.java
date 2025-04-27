package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.SaldoDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.exception.ChavePixJaExisteException;
import com.well.banco_digital_CDBW.exception.ChavePixNaoExisteException;
import com.well.banco_digital_CDBW.exception.ContaNaoExisteException;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.exception.SaldoInsuficienteException;
import com.well.banco_digital_CDBW.repository.ContaRepository;

@Service
public class ContaService {
	
	
	private final ClienteService clienteService;

	//private final CartaoService cartaoService;
	
	private final ContaRepository contaRepository;
	
	public ContaService(ClienteService clienteService,
			//CartaoService cartaoService,
			ContaRepository contaRepository) {
		this.clienteService = clienteService;
		//this.cartaoService = cartaoService;
		this.contaRepository = contaRepository;
	}
	
	
	public ContaDto criarConta(Long id, ContaDto contaAbrir) {
		Cliente cliente = clienteService.buscarclientePorId(id);
		Conta conta = criar(cliente, contaAbrir);
		//cartaoService.criar(conta);
		conta = contaRepository.save(conta);
		conta.gerarNumeroConta(conta.getId());
		contaRepository.save(conta);
		
		return new ContaDto(conta);
	}
	
	public ContaDto cadastrarPixConta(Long idConta, Long idCliente, PixDto pix) {
		clienteService.buscarclientePorId(idCliente);
		validarPixUnico(pix.chavePix());  
		Conta conta = buscarContaPorIdContaIdCliente(idConta, idCliente);
		conta.setChavePix(pix.chavePix());
		contaRepository.save(conta);
		
		return new ContaDto(conta);
	}
	

	public ContaDto detalharConta(Long idConta, Long idCliente) {
		Conta conta = buscarContaPorIdContaIdCliente(idConta, idCliente);
		return new ContaDto(conta);
	}
	
	public SaldoDto buscarSaldoConta(Long idConta, Long idCliente) {
		BigDecimal saldo = buscarContaPorIdContaIdCliente(idConta, idCliente)
				.getSaldo();		
		return new SaldoDto(saldo);
	}
	
	public Conta buscarContaPorIdContaIdCliente(Long idConta, Long id) {
		return contaRepository.findByIdAndClienteId(idConta, id)
				.orElseThrow(() -> new ContaNaoExisteException());
	}


	public Conta buscarContaPorPix(String chavePix) {
		return contaRepository.findByChavePix(chavePix)
				.orElseThrow(() -> new ChavePixNaoExisteException());
	}
	
	
	private void validarPixUnico(String chavePix) {
		contaRepository.findByChavePix(chavePix)
				.ifPresent(c -> {throw new ChavePixJaExisteException();});		
	}


	public void validarSaldoSufuciente(BigDecimal saldo,BigDecimal valor) {
		BigDecimal valorResto = saldo.subtract(valor);
		if(valorResto.compareTo(BigDecimal.ZERO) <= 0) {
			throw new SaldoInsuficienteException();
		}
	}

	public Conta buscarContaPorId(Long idContaDestino) {
		return contaRepository.findById(idContaDestino)
				.orElseThrow(() -> new ContaNaoExisteException());
		}

	private Conta criar(Cliente cliente, ContaDto contaAbrir) {
		if(contaAbrir.tipoConta().toUpperCase().equals("CONTA CORRENTE")) {
			return new ContaCorrente(cliente);
		}else if(contaAbrir.tipoConta().toUpperCase().equals("CONTA POUPANÇA")){
			ContaPoupanca conta = new ContaPoupanca(cliente);
			conta.setRendimento(0.5);			
			return conta;
		}else {
			throw new CriarContaException();
		}
	}


	public List<Conta> buscarContaPorData(int diaDoMes) {
		return contaRepository.findAllByDayMonth(diaDoMes);
	}

	public void atualizarSaldoConta(Conta conta) {
		contaRepository.save(conta);		
	}

	
	

}
