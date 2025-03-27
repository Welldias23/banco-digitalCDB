package com.well.banco_digital_CDBW.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.exception.MenorDeIdadeException;
import com.well.banco_digital_CDBW.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public boolean cpfExiste(String cpf) {
		return clienteRepository.existsByCpf(cpf);
	}

	public boolean deMaior(LocalDate dataNascimento) {
		var agora = LocalDate.now();
		var periodo = Period.between(dataNascimento, agora);
		
		if(periodo.getYears() > 18) {
			return true;
		}
		throw new MenorDeIdadeException();
	}

}
