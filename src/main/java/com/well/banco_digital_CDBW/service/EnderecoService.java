package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.dto.EnderecoReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Endereco;
import com.well.banco_digital_CDBW.exception.EnderecoCadastrarException;
import com.well.banco_digital_CDBW.exception.EnderecoIdNaoExisteException;
import com.well.banco_digital_CDBW.exception.NaotemEnderecoException;
import com.well.banco_digital_CDBW.repository.EnderecoRepository;

import jakarta.validation.Valid;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco cadastrar(EnderecoReqDto enderecoDto) {
		//criar um metodo para verificar cep
		var endereco = new Endereco(enderecoDto);
		enderecoRepository.save(endereco);
		
		return endereco;
	}

	public Endereco detalhar(Long id) {
		enderedoIdExiste(id);
		//criar metodo para verficar e o endereco pertence ao cliente logado
		var endereco = enderecoRepository.getReferenceById(id);
		return endereco;
	}
	
	public Endereco atualizar(Cliente cliente, EnderecoReqDto enderecoAtualizar) {
		var endereco = cliente.getEndereco();
		endereco.atualizar(enderecoAtualizar);
		enderecoRepository.save(endereco);
		return endereco;
	}

	private void enderedoIdExiste(Long id) {
		if(!enderecoRepository.existsById(id)) {
			throw new EnderecoIdNaoExisteException();
		}	
	}

	public void JaTemEnderecoCadastrado(Endereco endereco) {
		if(endereco != null) {
			throw new EnderecoCadastrarException();
		}
	}

	public Endereco temEndereco(Endereco endereco) {
		if(endereco == null) {
			throw new NaotemEnderecoException();
		}
		
		return endereco;
	}

	public void excluir(Long id) {
		enderecoRepository.deleteById(id);
	}


}
