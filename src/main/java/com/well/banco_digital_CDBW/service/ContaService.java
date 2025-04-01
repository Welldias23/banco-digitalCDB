package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ContaAAbrir;
import com.well.banco_digital_CDBW.entity.CategoriaCliente;
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
			var conta = new ContaCorrente(cliente);
			atribuirTaxaManutencao(cliente, conta);
			
			return conta;
		}else if(contaAbrir.tipoConta().toUpperCase().equals("CONTA POUPANÇA")){
			var conta = new ContaPoupanca(cliente);
			conta.setRendimento(0.5);
			
			return conta;
		}else {
			throw new CriarContaException();
		}
	}

	private void atribuirTaxaManutencao(Cliente cliente, ContaCorrente conta) {
		if(cliente.getCategoria().equals(CategoriaCliente.COMUM)) {
			conta.setTaxaManutencao(30.0);
		} else if(cliente.getCategoria().equals(CategoriaCliente.PREMIU)) {
			conta.setTaxaManutencao(15.0);
		} else if(cliente.getCategoria().equals(CategoriaCliente.SUPER)) {
			conta.setTaxaManutencao(1.0);
		} else {
			throw new CriarContaException();
		}
		
	}

}
