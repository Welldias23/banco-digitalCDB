package com.well.banco_digital_CDBW.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.entity.Conta;
import com.well.banco_digital_CDBW.entity.ContaCorrente;
import com.well.banco_digital_CDBW.entity.TaxaManuntencao;
import com.well.banco_digital_CDBW.repository.TransacaoRepository;

@Service
public class ManutencaoService {
	
	@Autowired 
	private ContaService contaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Scheduled(cron = "${spring.task.scheduling.cron}")
	public void manuntecaoMensal() {
		var data = LocalDate.now();
		var contas = contaService.buscarPorData(data.getDayOfMonth());
		for (Conta conta : contas) {
			if(conta.getClass() == ContaCorrente.class) {
				var cliente = clienteService.buscarclientePorId(conta.getCliente().getId());
				conta.debitar(cliente.getCategoria().getTaxaManuntencao());
				var manutencao = new TaxaManuntencao(conta, cliente.getCategoria().getTaxaManuntencao());
				manutencao.aplicar();
				transacaoRepository.save(manutencao);
			}
		}
	}


}
