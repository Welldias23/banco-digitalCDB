package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.exception.ChavePixJaExisteException;
import com.well.banco_digital_CDBW.exception.ChavePixNaoExisteException;
import com.well.banco_digital_CDBW.exception.ContaNaoExisteException;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.exception.SaldoInsuficienteException;
import com.well.banco_digital_CDBW.mapper.ContaMapper;
import com.well.banco_digital_CDBW.repository.ContaRepository;

@Service
public class ContaService {
	
	
	private final ClienteService clienteService;
	private final CartaoDebitoService cartaoDebitoService;	
	private final ContaRepository contaRepository;
	private final ContaMapper mapper;
	
	public ContaService(ClienteService clienteService,
			CartaoDebitoService cartaoDebitoService,
			ContaRepository contaRepository,
			ContaMapper mapper) {
		this.clienteService = clienteService;
		this.cartaoDebitoService = cartaoDebitoService;
		this.contaRepository = contaRepository;
		this.mapper = mapper;
	}
	
	
	public ContaDto criarConta(Long id, ContaDto contaAbrir) {
		Cliente cliente = clienteService.buscarclientePorId(id);
		Conta conta = criar(cliente, contaAbrir);
		conta = contaRepository.save(conta);
		conta.gerarNumeroConta(conta.getId());
		contaRepository.save(conta);
		cartaoDebitoService.criarCartaoDebito(conta, contaAbrir.cartao());
		
		return mapper.toContaDto(conta);
	}
	
	public ContaDto cadastrarPixConta(Long idConta, Long idCliente, PixDto pix) {
		clienteService.buscarclientePorId(idCliente);
		validarPixUnico(pix.chavePix());  
		Conta conta = buscarContaPorIdContaIdCliente(idConta, idCliente);
		conta.setChavePix(pix.chavePix());
		contaRepository.save(conta);
		
		return mapper.toContaDto(conta);
	}
	

	public ContaDto detalharConta(Long idConta, Long idCliente) {
		Conta conta = buscarContaPorIdContaIdCliente(idConta, idCliente);
		return mapper.toContaDto(conta);
	}
	
	public ContaDto buscarSaldoConta(Long idConta, Long idCliente) {
		Conta conta = buscarContaPorIdContaIdCliente(idConta, idCliente);	
		
		return mapper.toContaDto(conta);
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


	public void debitarSaldoConta(Conta conta, BigDecimal valor) {
		validarSaldoSufuciente(conta.getSaldo(), valor);
		conta.debitar(valor);
		contaRepository.save(conta);		
	}

	
	

}
