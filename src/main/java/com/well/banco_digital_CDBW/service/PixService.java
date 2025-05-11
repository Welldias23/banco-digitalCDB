package com.well.banco_digital_CDBW.service;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.TransferenciaPix;
import com.well.banco_digital_CDBW.mapper.TransferenciaMapper;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class PixService {
	
	private final ContaService contaService;
	private final ClienteService clienteService;
	private final ContaRepository contaRepository;
	private final TransacaoRepository transacaoRepository;
	private final TransferenciaMapper mapper;
	
	public PixService(ContaService contaService, ClienteService clienteService,
			ContaRepository contaRepository, TransacaoRepository transacaoRepository,
			TransferenciaMapper mapper) {
		this.clienteService = clienteService;
		this.contaService = contaService;
		this.contaRepository = contaRepository;
		this.transacaoRepository = transacaoRepository;
		this.mapper = mapper;
	}
	
	public TransferenciaDto transferir(Cliente clienteOrigen, Long idConta, TransferenciaPixReqDto transferenciaPixAFazer) {
		clienteService.buscarclientePorId(clienteOrigen.getId());
		Conta contaOrigem = contaService.buscarContaPorIdContaIdCliente(idConta, clienteOrigen.getId());
		contaService.validarSaldoSufuciente(contaOrigem.getSaldo(), transferenciaPixAFazer.valor());
		Conta contaDestino = contaService.buscarContaPorPix(transferenciaPixAFazer.chavePix());	
		TransferenciaPix transferenciaPix = new TransferenciaPix(contaOrigem, contaDestino, transferenciaPixAFazer.valor());
		transferenciaPix.aplicar();
		transferenciaPix.setNomeOrigem(contaOrigem.getCliente().getNome());
		transferenciaPix.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		contaRepository.save(contaOrigem);
		transacaoRepository.save(transferenciaPix);
		
		return mapper.toTransferenciaDto(transferenciaPix);
	}
	

}
