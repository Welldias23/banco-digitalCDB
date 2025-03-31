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
import com.well.banco_digital_CDBW.dto.ClienteRequest;
import com.well.banco_digital_CDBW.dto.ContaAAbrir;
import com.well.banco_digital_CDBW.dto.ContaDTO;
import com.well.banco_digital_CDBW.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContasController {
	@Autowired
	private ContaService contaService;

	@PostMapping("/cadastrar/{id}")
	//refatorar esse metodo
	public ResponseEntity<ContaDTO> cadastrar(@PathVariable Long id, @RequestBody ContaAAbrir contaAAbrir){
		var conta = contaService.criarConta(id, contaAAbrir);
		return ResponseEntity.ok(new ContaDTO(conta));	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> dados(@PathVariable Long id) {
		
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ContaDTO> atualizar(@RequestBody @Valid ClienteAtualizadoDto clienteAtualizar, @PathVariable Long id) {
		
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContaDTO> excluir(@PathVariable Long id) {
		
		return null;
	}

}
