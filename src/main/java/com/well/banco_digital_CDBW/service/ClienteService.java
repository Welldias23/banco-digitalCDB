package com.well.banco_digital_CDBW.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Cliente cadastrar(ClienteReqDto clienteReq) {		
		cpfUnico(clienteReq.cpf());	
		emailUnico(clienteReq.email());
		deMaior(clienteReq.dataNascimento());
		var cliente = new Cliente(clienteReq);
		cliente.setCategoria(categoria(clienteReq.rendaMensal()));
		cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		clienteRepository.save(cliente);
		return cliente;
	}


	public Cliente detalhar(Long id) {
		idExiste(id);
		var cliente = clienteRepository.getReferenceById(id);
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(ClienteAtualizadoDto clienteAtualizar, Long id) {
		idExiste(id);
		var cliente = clienteRepository.getReferenceById(id);
		cliente.atualizarDados(clienteAtualizar);
		if(clienteAtualizar.rendaMensal() != null) {
			cliente.setCategoria(categoria(clienteAtualizar.rendaMensal()));
		}
		if(clienteAtualizar.senha() != null) {
			cliente.setSenha(passwordEncoder.encode(cliente.getPassword()));
		}
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@Transactional
	public void excluir(Long id) {
		idExiste(id);
		clienteRepository.deleteById(id);	
	}
	

	public Cliente clienteCpf(String cpf) {
		var cliente = clienteRepository.getReferenceByCpf(cpf);
		return  cliente;
	}
	
	@Transactional
	public Cliente clienteId(Long id) {
		idExiste(id);
		var cliente = clienteRepository.findById(id);
		return  cliente;
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

	@Transactional
	private void idExiste(Long id) {
		if(!clienteRepository.existsById(id)) {
			throw new ClienteIdNaoExisteException();
		}
	}

	public CategoriaCliente categoria(BigDecimal rendaMensal) {
		CategoriaCliente categoria;
		if(rendaMensal.doubleValue() <= 1512.00) {
			categoria = CategoriaCliente.COMUM;
		}else if (rendaMensal.doubleValue() <= 3000.00) {
			categoria = CategoriaCliente.SUPER;
		} else {
			categoria = CategoriaCliente.PREMIUM;
		}
		return categoria;
	}


	public void removerEndereco(Cliente cliente) {
		cliente.setEndereco(null);
		System.out.println(cliente.getEndereco());
		clienteRepository.save(cliente);
	}


}
