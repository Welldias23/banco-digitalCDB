package com.well.banco_digital_CDBW.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.entity.Conta;


@Service
public class ManuntencaoMensalService {
	
	@Autowired
	private ContaService contaService;

	@Scheduled(cron = "${spring.task.scheduling.cron}")
	public void manuntecao() {
		var data = LocalDate.now();
		var contas = contaService.buscarData(data.getDayOfMonth());
		for (Conta conta : contas) {
			conta.debitar(null);
		}
	}
}
