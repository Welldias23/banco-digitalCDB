package com.well.banco_digital_CDBW.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.dto.Complete;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.ClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/cadastrar")
	@JsonView(View.Get.class)
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Validated(Complete.class) ClienteDto clienteReq, 
			UriComponentsBuilder uriBuilder){		
		ClienteDto cliente = clienteService.cadastrarCliente(clienteReq);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.id()).toUri();
		return ResponseEntity.created(uri).body(cliente);
		
	}
	
	@GetMapping
	@JsonView(View.Get.class)
	public ResponseEntity<ClienteDto> detalharCliente(@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.detalharCliente(clienteLogado);
		return ResponseEntity.ok(cliente);	
	}
	
	@PutMapping
	@JsonView(View.Get.class)
	public ResponseEntity<ClienteDto> atualizarCliente(@RequestBody @Validated(Complete.class) ClienteDto clienteAtualizar, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.atualizarCliente(clienteAtualizar, clienteLogado);
		return ResponseEntity.ok(cliente);
	}
	
	@PatchMapping
	@JsonView(View.Get.class)
	public ResponseEntity<ClienteDto> atualizarParcialmenteCliente(@RequestBody @Validated ClienteDto clienteAtualizar, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.atualizarCliente(clienteAtualizar, clienteLogado);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping
	public ResponseEntity<ClienteDto> excluirCliente(@AuthenticationPrincipal Cliente clienteLogado) {
		clienteService.excluirCliente(clienteLogado);
		return ResponseEntity.noContent().build();
	}
	
}


















