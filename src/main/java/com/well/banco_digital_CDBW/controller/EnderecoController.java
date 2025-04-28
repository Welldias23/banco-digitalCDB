package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.Complete;
import com.well.banco_digital_CDBW.dto.EnderecoDto;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.EnderecoService;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@PostMapping("/cadastrar")
	@JsonView(View.Get.class)
	public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody @Validated(Complete.class) EnderecoDto enderecoReq, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		EnderecoDto endereco = enderecoService.cadastrarCliente(enderecoReq, clienteLogado);
		
		return ResponseEntity.created(null).body(endereco);
	}
	
	@GetMapping
	@JsonView(View.Get.class)
	public ResponseEntity<EnderecoDto> detalharEndereco(@AuthenticationPrincipal Cliente clienteLogado){
		EnderecoDto endereco = enderecoService.detalharEndereco(clienteLogado);
		
		return ResponseEntity.ok(endereco);
	}
	
	@PutMapping("/atualizar")
	@JsonView(View.Get.class)
	public ResponseEntity<EnderecoDto> atualizarEndereco(@RequestBody @Validated(Complete.class) EnderecoDto enderecoAtualizar,
			@AuthenticationPrincipal Cliente clienteLogado){
		EnderecoDto enderecoAtualizado = enderecoService.atualizarEndereco(clienteLogado, enderecoAtualizar);
		
		return ResponseEntity.ok(enderecoAtualizado);
	}
	
	@PatchMapping("/atualizar")
	@JsonView(View.Get.class)
	public ResponseEntity<EnderecoDto> atualizarParcialmenteEndereco(@RequestBody @Validated EnderecoDto enderecoAtualizar,
			@AuthenticationPrincipal Cliente clienteLogado){
		EnderecoDto enderecoAtualizado = enderecoService.atualizarEndereco(clienteLogado, enderecoAtualizar);
		
		return ResponseEntity.ok(enderecoAtualizado);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> excluirEndereco(@AuthenticationPrincipal Cliente clienteLogado){
		enderecoService.excluirEndereco(clienteLogado);
		
		return ResponseEntity.noContent().build();
	}
}
