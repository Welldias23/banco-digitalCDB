package com.well.banco_digital_CDBW.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.ClienteIdNaoExisteException;
import com.well.banco_digital_CDBW.exception.CpfUnicoException;
import com.well.banco_digital_CDBW.exception.EmailUnicoException;
import com.well.banco_digital_CDBW.exception.MenorDeIdadeException;
import com.well.banco_digital_CDBW.mapper.ClienteMapper;
import com.well.banco_digital_CDBW.repository.ClienteRepository;

import jakarta.transaction.Transactional;


@Service
public class ClienteService {
	

	private final ClienteRepository clienteRepository;	
	private final PasswordEncoder passwordEncoder;
	private final ClienteMapper mapper;
	
	public ClienteService(ClienteRepository clienteRepository,PasswordEncoder passwordEncoder,
			ClienteMapper mapper) {
		this.clienteRepository = clienteRepository;
		this.passwordEncoder = passwordEncoder;
		this.mapper = mapper;
	}
	
	
	
	@Transactional
	public ClienteDto cadastrarCliente(ClienteDto clienteReq) {		
		validarCliente(clienteReq);
		var cliente = new Cliente(mapper.toCliente(clienteReq));
		cliente.definirCategoria(clienteReq);
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		
		return mapper.toClienteDto(cliente);
	}


	public ClienteDto detalharCliente(Cliente clienteLogado) {		
		 Cliente cliente = buscarclientePorId(clienteLogado.getId());
		 
		 return mapper.toClienteDto(cliente);
	}
	
	@Transactional
	public ClienteDto atualizarCliente(ClienteDto clienteAtualizar, Cliente clienteLogado) {
		validarCliente(clienteAtualizar);
		Cliente cliente = buscarclientePorId(clienteLogado.getId());
		cliente.atualizarCliente(mapper.toCliente(clienteAtualizar));
		cliente.definirCategoria(clienteAtualizar);
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		
		return mapper.toClienteDto(cliente);
	}
	
	@Transactional
	public void excluirCliente(Cliente clienteLogado) {
		buscarclientePorId(clienteLogado.getId());
		clienteRepository.deleteById(clienteLogado.getId());	
	}
	

	public Cliente buscarClientePorCpf(String cpf) {
		return clienteRepository.getReferenceByCpf(cpf);
	}
	
	@Transactional
	public Cliente buscarclientePorId(Long id) {
		return  clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteIdNaoExisteException());
	}
	
	public void validarCliente(ClienteDto clienteReq) {
		Optional.ofNullable(clienteReq.cpf()).ifPresent(this::validarCpfUnico);
		Optional.ofNullable(clienteReq.email()).ifPresent(this::ValidarEmailUnico);
		Optional.ofNullable(clienteReq.dataNascimento()).ifPresent(this::deMaior);
	}

	private void validarCpfUnico(String cpf) {
		Optional.of(cpf)
					.filter(c -> clienteRepository.existsByCpf(c))
					.ifPresent(c -> {throw new CpfUnicoException();});
	}
	
	
	private void ValidarEmailUnico(String email) {
		Optional.of(email)
						.filter(e -> clienteRepository.existsByEmail(e))
						.ifPresent(e -> {throw new EmailUnicoException();});
	}

	private void deMaior(LocalDate dataNascimento) {
		LocalDate agora = LocalDate.now();
		Period periodo = Period.between(dataNascimento, agora);
		if(periodo.getYears() < 18) {
			throw new MenorDeIdadeException();
		}		
	}


	public void removerEndereco(Cliente cliente) {
		cliente.setEndereco(null);
		clienteRepository.save(cliente);
	}



}
