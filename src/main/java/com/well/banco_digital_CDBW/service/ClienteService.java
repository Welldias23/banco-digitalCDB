package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteReqDto;
import com.well.banco_digital_CDBW.entity.CategoriaCliente;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.ClienteIdNaoExisteException;
import com.well.banco_digital_CDBW.exception.CpfUnicoException;
import com.well.banco_digital_CDBW.exception.EmailUnicoException;
import com.well.banco_digital_CDBW.exception.MenorDeIdadeException;
import com.well.banco_digital_CDBW.repository.ClienteRepository;

import jakarta.transaction.Transactional;


@Service
public class ClienteService {
	

	private final ClienteRepository clienteRepository;	
	private final PasswordEncoder passwordEncoder;
	
	public ClienteService(ClienteRepository clienteRepository,PasswordEncoder passwordEncoder) {
		this.clienteRepository = clienteRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
	@Transactional
	public Cliente cadastrarCliente(ClienteReqDto clienteReq) {		
		validarCliente(clienteReq);
		var cliente = new Cliente(clienteReq);
		cliente.definirCategoria(clienteReq);
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		return cliente;
	}


	public Cliente detalharCliente(Long id) {		
		return buscarclientePorId(id);
	}
	
	@Transactional
	public Cliente atualizarCliente(ClienteReqDto clienteReq, Long id) {
		//validar antes de atualizar
		var cliente = buscarclientePorId(id);
		cliente.atualizarDados(clienteReq);
		cliente.definirCategoria(clienteReq);
		return cliente;
	}
	
	@Transactional
	public void excluirCliente(Long id) {
		buscarclientePorId(id);
		clienteRepository.deleteById(id);	
	}
	

	public Cliente buscarClientePorCpf(String cpf) {
		return clienteRepository.getReferenceByCpf(cpf);
	}
	
	@Transactional
	public Cliente buscarclientePorId(Long id) {
		return  clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteIdNaoExisteException());
	}
	
	public void validarCliente(ClienteReqDto clienteReq) {
		Optional.ofNullable(clienteReq.cpf()).ifPresent(this::cpfUnico);
		Optional.ofNullable(clienteReq.email()).ifPresent(this::emailUnico);
		Optional.ofNullable(clienteReq.dataNascimento()).ifPresent(this::deMaior);
	}

	private void cpfUnico(String cpf) {
		if(clienteRepository.existsByCpf(cpf)) {
			throw new CpfUnicoException();
		}
	}
	
	
	private void emailUnico(String email) {
		if(clienteRepository.existsByEmail(email)) {
			throw new EmailUnicoException();
		}
		
	}

	private void deMaior(LocalDate dataNascimento) {
		var agora = LocalDate.now();
		var periodo = Period.between(dataNascimento, agora);
		if(periodo.getYears() < 18) {
			throw new MenorDeIdadeException();
		}		
	}


	public void removerEndereco(Cliente cliente) {
		cliente.setEndereco(null);
		System.out.println(cliente.getEndereco());
		clienteRepository.save(cliente);
	}



}
