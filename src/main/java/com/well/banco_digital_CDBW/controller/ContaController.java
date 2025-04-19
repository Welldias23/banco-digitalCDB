package com.well.banco_digital_CDBW.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.ContaReqDto;
import com.well.banco_digital_CDBW.dto.ContaResDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.SaldoDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.CartaoService;
import com.well.banco_digital_CDBW.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {
	@Autowired
	private ContaService contaService;
	

	@Autowired
	private CartaoService cartaoService;
	

	@PostMapping
	public ResponseEntity<ContaResDto> cadastrar( @RequestBody ContaReqDto contaAAbrir, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var conta = contaService.criar(clienteLogado.getId(), contaAAbrir);
		cartaoService.criar(conta);
		return ResponseEntity.ok(new ContaResDto(conta));	
	}
	
	@PostMapping("/pix")
	public ResponseEntity<ContaResDto> cadastrarPix(@RequestBody @Valid PixDto pix, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var conta = contaService.cadastrarPix(clienteLogado, pix);
		
		return ResponseEntity.ok(new ContaResDto(conta));
	}
	
	@GetMapping("/{idConta}")
	public ResponseEntity<ContaResDto> detalharUma(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		var conta = contaService.buscarPorIdContaIdCliente(idConta, clienteLogado.getId());
		return ResponseEntity.ok(new ContaResDto(conta));
	}
	
	@GetMapping("/saldo/{idConta}")
	public ResponseEntity<SaldoDto> consultarSaldo(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		var saldo = contaService.buscarSaldo(idConta, clienteLogado.getId());
		
		return ResponseEntity.ok(new SaldoDto(saldo));
	}
	
	
}




















