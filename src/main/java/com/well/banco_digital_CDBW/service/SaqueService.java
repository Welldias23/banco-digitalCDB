package com.well.banco_digital_CDBW.service;


import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.SaqueDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.Saque;
import com.well.banco_digital_CDBW.mapper.SaqueMapper;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class SaqueService {
	
	private final ClienteService clienteService;
	private final ContaService contaService;
	private final ContaRepository contaRepository;
	private final TransacaoRepository transacaoRepository;
	private final SaqueMapper mapper;

	public SaqueService(ClienteService clienteService, ContaService contaService,
			ContaRepository contaRepository, TransacaoRepository transacaoRepository, SaqueMapper mapper) {
		this.clienteService = clienteService;
		this.contaService = contaService;
		this.contaRepository = contaRepository;
		this.transacaoRepository = transacaoRepository;
		this.mapper = mapper;
	} 
	
	public SaqueDto sacar(Cliente clienteLogado, Long idConta, SaqueDto saque) {
		clienteService.buscarclientePorId(clienteLogado.getId());
		Conta conta = contaService.buscarContaPorIdContaIdCliente(idConta, clienteLogado.getId());
		contaService.validarSaldoSufuciente(conta.getSaldo(), saque.valor());		
		Saque saqueFeito = new Saque(conta, saque.valor());
		saqueFeito.aplicar();
		contaRepository.save(conta);
		transacaoRepository.save(saqueFeito);
		
		return mapper.toSaqueDto(saqueFeito);
	}
}
