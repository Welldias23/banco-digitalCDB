package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.dto.ClienteRequest;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Endereco;
import com.well.banco_digital_CDBW.exception.CpfJaExistenteException;
import com.well.banco_digital_CDBW.repository.ClienteRepository;
import com.well.banco_digital_CDBW.repository.EnderecoRepository;
import com.well.banco_digital_CDBW.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteRequest clienteReq, UriComponentsBuilder uriBuilder){
		
		var cliente = clienteService.cadastrar(clienteReq);
		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity dados(@PathVariable Long id) {
		var cliente = clienteService.detalhar(id);
		//REFATORAR ESSA PARTE, O CLIENTE DEVE DEVOLVER UM DTO,E ELA DEVE RETORNAR O CLIENTE LOGADO
		return ResponseEntity.ok(cliente);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizar(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @PathVariable Long id) {
		var cliente = clienteService.atualizar(clienteAtualizar, id);
		return ResponseEntity.ok(new ClienteDto(cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}


















