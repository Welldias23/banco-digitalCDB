package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.DepositoReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Deposito;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class DepositoService {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;


	public Deposito depositar(Cliente clienteLogado, DepositoReqDto deposito) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		var contaDestino = contaService.buscarPorId(deposito.idContaDestino());
		var depositoEfetuado = new Deposito(contaDestino, deposito.valor());
		depositoEfetuado.aplicar();
		depositoEfetuado.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		transacaoRepository.save(depositoEfetuado);
		
		return depositoEfetuado;
	}
}
