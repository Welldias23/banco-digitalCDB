package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteResDto;
import com.well.banco_digital_CDBW.dto.ClienteReqDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteResDto> cadastrar(@RequestBody @Valid ClienteReqDto clienteReq, UriComponentsBuilder uriBuilder){		
		var cliente = clienteService.cadastrar(clienteReq);
		var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteResDto(cliente));
		
	}
	
	@GetMapping
	public ResponseEntity<ClienteResDto> dados(@AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		var cliente = clienteService.detalhar(id);
		return ResponseEntity.ok(new ClienteResDto(cliente));	
	}
	
	@PutMapping
	public ResponseEntity<ClienteResDto> atualizar(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		var cliente = clienteService.atualizar(clienteAtualizar, id);
		return ResponseEntity.ok(new ClienteResDto(cliente));
	}

	@DeleteMapping
	public ResponseEntity<ClienteResDto> excluir(@AuthenticationPrincipal Cliente clienteLogado) {
		var id = clienteLogado.getId();
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}


















