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
import com.well.banco_digital_CDBW.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;

	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteRequest clienteReq, UriComponentsBuilder uriBuilder){
		var cliente = new Cliente(clienteReq);
		
		repository.save(cliente);
		
		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity dadosCliente(@PathVariable Long id) {
		var cliente = repository.findById(id);
		
		//REFATORAR ESSA PARTE, O CLIENTE DEVE DEVOLVER UM DTO
		return ResponseEntity.ok(cliente);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizarCliente(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @PathVariable Long id) {
		var cliente = repository.getReferenceById(id);
		cliente.atualizarDados(clienteAtualizar);
		
		repository.save(cliente);
		
		return ResponseEntity.ok(new ClienteDto(cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		var cliente = repository.getReferenceById(id);
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}


















