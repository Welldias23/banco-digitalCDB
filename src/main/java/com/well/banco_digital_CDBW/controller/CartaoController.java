package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.CartaoReqDto;
import com.well.banco_digital_CDBW.dto.CartaoResDto;
import com.well.banco_digital_CDBW.dto.NovaSenhaDto;
import com.well.banco_digital_CDBW.dto.NovoLimiteDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.CartaoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cartao")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService; 
	
	@PostMapping("/{idConta}/emitir")
	public ResponseEntity<CartaoResDto> criarCartaoCredito(@RequestBody CartaoReqDto cartaoACriar, 
			@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente cliente){
		var cartao = cartaoService.criar(cliente, idConta, cartaoACriar);
		return ResponseEntity.ok(cartao);
	}
	
	@GetMapping("/{idCartao}")
	public ResponseEntity<CartaoResDto> buscar(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoService.detalhar(idCartao, clienteLogado);
		return ResponseEntity.ok(cartao);
	}
	
	@PutMapping("/{idCartao}/limite")
	public ResponseEntity<CartaoResDto> alterarLimiteCredito(@PathVariable Long idCartao, 
			@RequestBody @Valid NovoLimiteDto limite, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoService.alterarLimiteCredito(idCartao, clienteLogado, limite.novoLimite());
		
		return ResponseEntity.ok(cartao);
	}
	
	@PutMapping("/{idCartao}/ativar")
	public ResponseEntity<Void> ativarStatus(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.ativarStatus(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/desativar")
	public ResponseEntity<Void> desativarStatus(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.desativarStatus(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/senha ")
	public ResponseEntity<CartaoResDto> alterarSenha(@PathVariable Long idCartao, @RequestBody @Valid NovaSenhaDto senha){
		cartaoService.alterarSenha(idCartao, senha.novaSenha());
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/limite-diario")
	public ResponseEntity<CartaoResDto> alterarLimiteDiario(@PathVariable Long idCartao, @RequestBody @Valid NovoLimiteDto limite){
		var cartao = cartaoService.alterarLimiteDiario(idCartao, limite.novoLimite());
		
		return ResponseEntity.ok(new CartaoResDto(cartao));
	}
	

}
