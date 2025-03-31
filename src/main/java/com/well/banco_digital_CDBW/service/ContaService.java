package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaAAbrir;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.ContaPoupanca;
import com.well.banco_digital_CDBW.exception.CriarContaException;
import com.well.banco_digital_CDBW.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaRepository contaRepository;
	
	public Conta criarConta(Long id, ContaAAbrir contaAbrir) {
		var cliente = clienteService.clienteId(id);
		var conta = criar(cliente, contaAbrir);
		contaRepository.save(conta);
		return conta;
	}

	private Conta criar(Cliente cliente, ContaAAbrir contaAbrir) {
		if(contaAbrir.tipoConta().toUpperCase().equals("CONTA CORRENTE")) {
			return new ContaCorrente(cliente);
		}else if(contaAbrir.tipoConta().toUpperCase().equals("CONTA POUPANÇA")){
			return new ContaPoupanca(cliente);
		}else {
			throw new CriarContaException();
		}
	}

}
