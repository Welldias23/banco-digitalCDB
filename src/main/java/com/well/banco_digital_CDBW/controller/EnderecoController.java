package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.EnderecoReqDto;
import com.well.banco_digital_CDBW.dto.EnderecoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ClienteService clienteService; 
	
	@PostMapping("/cadastrar")
	public ResponseEntity<EnderecoResDto> cadastrar(@RequestBody @Valid EnderecoReqDto enderecoReq, @AuthenticationPrincipal Cliente clienteLogado) {
		var cliente = clienteService.clienteId(clienteLogado.getId());
		var endereco = enderecoService.cadastrar(enderecoReq, cliente);
		
		return ResponseEntity.created(null).body(new EnderecoResDto(endereco));
	}
	
	@GetMapping
	public ResponseEntity<EnderecoResDto> detalhar(@AuthenticationPrincipal Cliente clienteLogado){
		var cliente = clienteService.clienteId(clienteLogado.getId());
		var endereco = enderecoService.temEndereco(cliente.getEndereco());
		
		return ResponseEntity.ok(new EnderecoResDto(endereco));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<EnderecoResDto> atualizar(@RequestBody @Valid EnderecoReqDto enderecoAtualizar,@AuthenticationPrincipal Cliente clienteLogado){
		var cliente = clienteService.clienteId(clienteLogado.getId());
		var enderecoAtualizado = enderecoService.atualizar(cliente, enderecoAtualizar);
		
		return ResponseEntity.ok(new EnderecoResDto(enderecoAtualizado));
	}
	
	@DeleteMapping
	public ResponseEntity<EnderecoResDto> excluir(@AuthenticationPrincipal Cliente clienteLogado){
		var cliente = clienteService.clienteId(clienteLogado.getId());
		clienteService.removerEndereco(cliente);
		
		return ResponseEntity.noContent().build();
	}
}
