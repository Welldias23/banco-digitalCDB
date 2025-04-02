package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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
		cliente.setCategoria(clienteService.categoria(clienteReq.rendaMensal()));
		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@GetMapping
	public ResponseEntity<ClienteDto> dados(@AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		var cliente = clienteService.detalhar(id);
		return ResponseEntity.ok(new ClienteDto(cliente));	
	}
	
	@PutMapping
	public ResponseEntity<ClienteDto> atualizar(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		var cliente = clienteService.atualizar(clienteAtualizar, id);
		return ResponseEntity.ok(new ClienteDto(cliente));
	}

	@DeleteMapping
	public ResponseEntity<ClienteDto> excluir(@AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}


















