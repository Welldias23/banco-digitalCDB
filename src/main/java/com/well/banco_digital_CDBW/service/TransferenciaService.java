package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Transferencia;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class TransferenciaService {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	public Transferencia transferir(Cliente clienteOrigem, Long idConta, TransferenciaReqDto transferenciaAFazer) {
		clienteService.buscarclientePorId(clienteOrigem.getId());
		var contaOrigem = contaService.buscarContaPorIdContaIdCliente(idConta, clienteOrigem.getId());
		contaService.validarSaldoSufuciente(contaOrigem.getSaldo(), transferenciaAFazer.valor());
		var contaDestino = contaService.buscarContaPorId(transferenciaAFazer.idContaDestino());  		
		var transferencia = new Transferencia(contaOrigem, contaDestino, transferenciaAFazer.valor());
		transferencia.aplicar();		
		transferencia.setNomeOrigem(contaOrigem.getCliente().getNome());
		transferencia.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		contaRepository.save(contaOrigem);
		transacaoRepository.save(transferencia);
		
		return transferencia;
	}
}
