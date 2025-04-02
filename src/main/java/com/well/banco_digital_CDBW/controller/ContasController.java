package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.well.banco_digital_CDBW.dto.ClienteRequest;
import com.well.banco_digital_CDBW.dto.ContaAAbrir;
import com.well.banco_digital_CDBW.dto.ContaDTO;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContasController {
	@Autowired
	private ContaService contaService;

	@PostMapping("/cadastrar")
	//refatorar esse metodo
	public ResponseEntity<ContaDTO> cadastrar( @RequestBody ContaAAbrir contaAAbrir, @AuthenticationPrincipal Cliente clienteLogado){
		var conta = contaService.criarConta(clienteLogado.getId(), contaAAbrir);
		return ResponseEntity.ok(new ContaDTO(conta));	
	}
	
	@GetMapping("/{idConta}")
	public ResponseEntity<ContaDTO> dados(@PathVariable Long id) {
		
		return null;
	}
	
	@PutMapping("/{idConta}")
	public ResponseEntity<ContaDTO> atualizar(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @PathVariable Long id) {
		
		return null;
	}

	@DeleteMapping("/{idConta}")
	public ResponseEntity<ContaDTO> excluir(@PathVariable Long id) {
		
		return null;
	}

}
