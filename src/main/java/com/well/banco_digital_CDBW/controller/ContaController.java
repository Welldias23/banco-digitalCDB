package com.well.banco_digital_CDBW.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.SaldoDto;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	private final ContaService contaService;
	
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	

	@PostMapping
	@JsonView(View.Get.class)
	public ResponseEntity<ContaDto> cadastrarConta( @RequestBody @Validated ContaDto contaAAbrir, 
			@AuthenticationPrincipal Cliente clienteLogado){
		ContaDto conta = contaService.criarConta(clienteLogado.getId(), contaAAbrir);
		
		return ResponseEntity.ok(conta);	
	}
	
	@PostMapping("/pix/{idConta}")
	@JsonView(View.Get.class)
	public ResponseEntity<ContaDto> cadastrarPixConta(@PathVariable Long idConta,
			@RequestBody @Valid PixDto pix, 
			@AuthenticationPrincipal Cliente clienteLogado){
		ContaDto conta = contaService.cadastrarPixConta(idConta, clienteLogado.getId(), pix);
		return ResponseEntity.ok(conta);
	}
	
	@GetMapping("/{idConta}")
	@JsonView(View.Get.class)
	public ResponseEntity<ContaDto> detalharUmaConta(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ContaDto conta = contaService.detalharConta(idConta, clienteLogado.getId());
		return ResponseEntity.ok(conta);
	}
	
	@GetMapping("/saldo/{idConta}")
	public ResponseEntity<SaldoDto> consultarSaldoConta(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		SaldoDto saldo = contaService.buscarSaldoConta(idConta, clienteLogado.getId());
		
		return ResponseEntity.ok(saldo);
	}
	
	
}




















