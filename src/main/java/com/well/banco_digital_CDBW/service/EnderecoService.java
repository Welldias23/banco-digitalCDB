package com.well.banco_digital_CDBW.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.EnderecoDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Endereco;
import com.well.banco_digital_CDBW.exception.ClienteJaPossuiEnderecoException;
import com.well.banco_digital_CDBW.exception.NaotemEnderecoException;
import com.well.banco_digital_CDBW.mapper.EnderecoMapper;
import com.well.banco_digital_CDBW.repository.EnderecoRepository;

import jakarta.transaction.Transactional;
@Service
public class EnderecoService {
	
	private final EnderecoRepository enderecoRepository;
	
	private final ClienteService clienteService;
	
	private final EnderecoMapper mapper;
	
	public EnderecoService(EnderecoRepository enderecoRepository,
						   ClienteService clienteService,
						   EnderecoMapper mapper) {
		this.enderecoRepository = enderecoRepository;
		this.clienteService = clienteService;
		this.mapper = mapper;
	}
	
	@Transactional
	public EnderecoDto cadastrarCliente(EnderecoDto enderecoDto, Cliente clienteLogado) {
		Cliente cliente = clienteService.buscarclientePorId(clienteLogado.getId());
		validarClienteJaTem(cliente.getEndereco());
		//criar um metodo para verificar cep
		Endereco endereco = new Endereco(mapper.toEndereco(enderecoDto), cliente);
		enderecoRepository.save(endereco);

		return mapper.toEnderecoDto(endereco);
	}
	

	public EnderecoDto detalharEndereco(Cliente clienteLogado) {
		Endereco endereco = buscarEnderecoPorCliente(clienteLogado);
		return mapper.toEnderecoDto(endereco);
	}

	@Transactional
	public EnderecoDto atualizarEndereco(Cliente clienteLogado, EnderecoDto enderecoAtualizar) {
		Endereco endereco = buscarEnderecoPorCliente(clienteLogado);
		endereco.atualizarCliente(mapper.toEndereco(enderecoAtualizar));
		enderecoRepository.save(endereco);
		return mapper.toEnderecoDto(endereco);
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

	
	private void validarClienteJaTem(Endereco endereco) {
		Optional.ofNullable(endereco).ifPresent(t -> {
			throw new ClienteJaPossuiEnderecoException();
		});
	}


}
