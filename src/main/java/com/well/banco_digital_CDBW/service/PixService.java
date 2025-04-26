package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.TransferenciaPix;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class PixService {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	public TransferenciaPix transferir(Cliente clienteOrigen, Long idConta, TransferenciaPixReqDto transferenciaPixAFazer) {
		clienteService.buscarclientePorId(clienteOrigen.getId());
		var contaOrigem = contaService.buscarPorIdContaIdCliente(idConta, clienteOrigen.getId());
		contaService.temSaldo(contaOrigem.getSaldo(), transferenciaPixAFazer.valor());
		var contaDestino = contaService.buscarPorPix(transferenciaPixAFazer.chavePix());	
		var transferenciaPix = new TransferenciaPix(contaOrigem, contaDestino, transferenciaPixAFazer.valor());
		transferenciaPix.aplicar();
		transferenciaPix.setNomeOrigem(contaOrigem.getCliente().getNome());
		transferenciaPix.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		contaRepository.save(contaOrigem);
		transacaoRepository.save(transferenciaPix);
		
		return transferenciaPix;
	}
	

}
