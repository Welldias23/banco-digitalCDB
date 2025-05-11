package com.well.banco_digital_CDBW.service;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.mapper.TransferenciaMapper;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class TransferenciaService {

	private final ClienteService clienteService;
	private final ContaRepository contaRepository;
	private final ContaService contaService;
	private final TransacaoRepository transacaoRepository;
	private final TransferenciaMapper mapper;
	
	public TransferenciaService(ClienteService clienteService, ContaRepository contaRepository,
			ContaService contaService, TransacaoRepository transacaoRepository, TransferenciaMapper mapper) {
		this.clienteService = clienteService;
		this.contaRepository = contaRepository;
		this.contaService = contaService;
		this.transacaoRepository = transacaoRepository;
		this.mapper = mapper;
	}

	public TransferenciaDto transferir(Cliente clienteOrigem, Long idConta, TransferenciaReqDto transferenciaAFazer) {
		clienteService.buscarclientePorId(clienteOrigem.getId());
		Conta contaOrigem = contaService.buscarContaPorIdContaIdCliente(idConta, clienteOrigem.getId());
		contaService.validarSaldoSufuciente(contaOrigem.getSaldo(), transferenciaAFazer.valor());
		Conta contaDestino = contaService.buscarContaPorId(transferenciaAFazer.idContaDestino());  		
		Transferencia transferencia = new Transferencia(contaOrigem, contaDestino, transferenciaAFazer.valor());
		transferencia.aplicar();		
		transferencia.setNomeOrigem(contaOrigem.getCliente().getNome());
		transferencia.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		contaRepository.save(contaOrigem);
		transacaoRepository.save(transferencia);
		
		return mapper.toTransferenciaDto(transferencia);
	}
}
