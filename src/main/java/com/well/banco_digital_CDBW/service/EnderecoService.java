package com.well.banco_digital_CDBW.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.EnderecoDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Endereco;
import com.well.banco_digital_CDBW.exception.EnderecoCadastrarException;
import com.well.banco_digital_CDBW.exception.EnderecoIdNaoExisteException;
import com.well.banco_digital_CDBW.exception.NaotemEnderecoException;
import com.well.banco_digital_CDBW.repository.EnderecoRepository;

import jakarta.transaction.Transactional;
@Service
public class EnderecoService {
	
	private final EnderecoRepository enderecoRepository;
	
	private final ClienteService clienteService;
	
	public EnderecoService(EnderecoRepository enderecoRepository,
						   ClienteService clienteService) {
		this.enderecoRepository = enderecoRepository;
		this.clienteService = clienteService;
	}
	
	@Transactional
	public EnderecoDto cadastrarCliente(EnderecoDto enderecoDto, Cliente clienteLogado) {
		Cliente cliente = clienteService.buscarclientePorId(clienteLogado.getId());
		//criar um metodo para verificar cep
		Endereco endereco = new Endereco(enderecoDto, cliente);
		enderecoRepository.save(endereco);

		return new EnderecoDto(endereco);
	}
	
	
	public EnderecoDto detalharEndereco(Cliente clienteLogado) {
		Endereco endereco = buscarEnderecoPorCliente(clienteLogado);
		return new EnderecoDto(endereco);
	}

	@Transactional
	public EnderecoDto atualizarEndereco(Cliente clienteLogado, EnderecoDto enderecoAtualizar) {
		Endereco endereco = buscarEnderecoPorCliente(clienteLogado);
		endereco.atualizarCliente(enderecoAtualizar);
		enderecoRepository.save(endereco);
		return new EnderecoDto(endereco);
	}

	
	@Transactional
	public void excluirEndereco(Cliente clienteLogado) {
		Endereco endereco = buscarEnderecoPorCliente(clienteLogado);
		enderecoRepository.deleteById(endereco.getId());
	}

	
	private Endereco buscarEnderecoPorCliente(Cliente clienteLogado) {
		return Optional.ofNullable(clienteLogado.getEndereco())
				.orElseThrow(() -> new NaotemEnderecoException());
	}



}
