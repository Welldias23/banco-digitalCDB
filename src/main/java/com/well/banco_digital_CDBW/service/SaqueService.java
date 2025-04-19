package com.well.banco_digital_CDBW.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.SaqueReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Saque;
import com.well.banco_digital_CDBW.repository.ContaRepository;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class SaqueService {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	public Saque sacar(Cliente clienteLogado, Long idConta, SaqueReqDto saque) {
		clienteService.clienteId(clienteLogado.getId());
		var conta = contaService.buscarPorIdContaIdCliente(idConta, clienteLogado.getId());
		contaService.temSaldo(conta.getSaldo(), saque.valor());		
		var saqueFeito = new Saque(conta, saque.valor());
		saqueFeito.aplicar();
		contaRepository.save(conta);
		transacaoRepository.save(saqueFeito);
		
		return saqueFeito;
	}
}
