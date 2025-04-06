package com.well.banco_digital_CDBW.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ContaReqDto;
import com.well.banco_digital_CDBW.dto.ContaResDto;
import com.well.banco_digital_CDBW.dto.DepositoReqDto;
import com.well.banco_digital_CDBW.dto.DepositoResDto;
import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.entity.Transacao;
import com.well.banco_digital_CDBW.service.ContaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContasController {
	@Autowired
	private ContaService contaService;

	@PostMapping("/cadastrar")
	public ResponseEntity<ContaResDto> cadastrar( @RequestBody ContaReqDto contaAAbrir, @AuthenticationPrincipal Cliente clienteLogado){
		var conta = contaService.criarConta(clienteLogado.getId(), contaAAbrir);
		return ResponseEntity.ok(new ContaResDto(conta));	
	}
	
	@GetMapping("/{idConta}")
	public ResponseEntity<ContaResDto> detalharUma(@PathVariable Long idConta, @AuthenticationPrincipal Cliente clienteLogado) {
		var conta = contaService.buscarUma(idConta, clienteLogado.getId());
		return ResponseEntity.ok(new ContaResDto(conta));
	}
	
	
	@GetMapping
	public ResponseEntity<List<ContaResDto>> detalharTodas(@AuthenticationPrincipal Cliente clienteLogado) {
		var contas = contaService.buscarTodas(clienteLogado.getId());
	    List<ContaResDto> contasDto = contas.stream().map(conta -> new ContaResDto(conta)).collect(Collectors.toList());
	    
		return ResponseEntity.ok(contasDto);
	}

	@DeleteMapping("/{idConta}")
	public ResponseEntity<ContaResDto> excluir(@PathVariable Long idConta) {
		
		return null;
	}
	
	@PostMapping("/deposito")
	public ResponseEntity<DepositoResDto> depositar(@RequestBody @Valid DepositoReqDto deposito, @AuthenticationPrincipal Cliente clienteLogado){
		var depositoFeito = contaService.depositar(clienteLogado, deposito);
		
		return ResponseEntity.ok(new DepositoResDto(depositoFeito));
	}
	
	@PostMapping("/transferencia")
	public ResponseEntity<TransferenciaResDto> transferir(@RequestBody @Valid TransferenciaReqDto transferenciaAFazer, @AuthenticationPrincipal Cliente clienteLogado){
		var transferencia = contaService.transferir(clienteLogado, transferenciaAFazer);
		
		return ResponseEntity.ok(new TransferenciaResDto(transferencia));
	}
	
	@PostMapping("/pix")
	public ResponseEntity<TransferenciaResDto> pix(@RequestBody @Valid TransferenciaPixReqDto transferenciaPixAFazer, @AuthenticationPrincipal Cliente clienteLogado){
		var transferenciaPix = contaService.transferirPix(clienteLogado, transferenciaPixAFazer);
		
		return ResponseEntity.ok(new TransferenciaResDto(transferenciaPix));
	}

}




















