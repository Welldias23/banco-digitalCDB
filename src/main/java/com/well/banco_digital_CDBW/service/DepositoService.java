package com.well.banco_digital_CDBW.service;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.DepositoDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.Deposito;
import com.well.banco_digital_CDBW.mapper.DepositoMapper;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class DepositoService {
	
	private final ClienteService clienteService;
	private final ContaService contaService;
	private final ContaRepository contaRepository;
	private final TransacaoRepository transacaoRepository;
	private final DepositoMapper mapper;
	

	public DepositoService( ClienteService clienteService, ContaService contaService,
			ContaRepository contaRepository, TransacaoRepository transacaoRepository,
			DepositoMapper mapper) {
		this.clienteService = clienteService;
		this.contaService = contaService;
		this.contaRepository = contaRepository;
		this.transacaoRepository = transacaoRepository;
		this.mapper = mapper;
	}

	public DepositoDto depositar(Cliente clienteLogado, DepositoDto deposito) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		Conta contaDestino = contaService.buscarContaPorId(deposito.idContaDestino());
		Deposito depositoEfetuado = new Deposito(contaDestino, deposito.valor());
		depositoEfetuado.aplicar();
		depositoEfetuado.setNomeDestino(contaDestino.getCliente().getNome());
		contaRepository.save(contaDestino);
		transacaoRepository.save(depositoEfetuado);
		
		return mapper.toDepositoDto(depositoEfetuado);
	}
}
