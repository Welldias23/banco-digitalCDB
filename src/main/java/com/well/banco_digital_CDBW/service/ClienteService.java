package com.well.banco_digital_CDBW.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteRequest;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.ClienteIdNaoExisteException;
import com.well.banco_digital_CDBW.exception.CpfUnicoException;
import com.well.banco_digital_CDBW.exception.MenorDeIdadeException;
import com.well.banco_digital_CDBW.repository.ClienteRepository;
import com.well.banco_digital_CDBW.repository.EnderecoRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Cliente cadastrar(ClienteRequest clienteReq) {		
		cpfUnico(clienteReq.cpf());		
		deMaior(clienteReq.dataNascimento());
		var cliente = new Cliente(clienteReq);
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		if(cliente.getEndereco() != null) {
			enderecoRepository.save(cliente.getEndereco());
		}
		return cliente;
	}
	
	public Cliente detalhar(Long id) {
		idExiste(id);
		var cliente = clienteRepository.getReferenceById(id);
		return cliente;
	}
	
	public Cliente atualizar(ClienteAtualizadoDto clienteAtualizar, Long id) {
		idExiste(id);
		var cliente = clienteRepository.getReferenceById(id);
		cliente.atualizarDados(clienteAtualizar);
		clienteRepository.save(cliente);
		return cliente;
	}
	

	public Cliente clienteCpf(String cpf) {
		var cliente = clienteRepository.getReferenceByCpf(cpf);
		return  cliente;
	}
	
	public void excluir(Long id) {
		idExiste(id);
		clienteRepository.deleteById(id);	
	}

	private void cpfUnico(String cpf) {
		if(clienteRepository.existsByCpf(cpf)) {
			throw new CpfUnicoException();
		}
	}

	private void deMaior(LocalDate dataNascimento) {
		var agora = LocalDate.now();
		var periodo = Period.between(dataNascimento, agora);
		if(periodo.getYears() < 18) {
			throw new MenorDeIdadeException();
		}		
	}
	
	private void idExiste(Long id) {
		if(!clienteRepository.existsById(id)) {
			throw new ClienteIdNaoExisteException();
		}
	}


}
