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
import com.well.banco_digital_CDBW.dto.CartaoCreditoResDto;
import com.well.banco_digital_CDBW.dto.CartaoDebitoResDto;
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
	public ResponseEntity<CartaoCreditoResDto> criarCartaoCredito(@RequestBody CartaoReqDto cartaoACriar, @PathVariable Long idConta, @AuthenticationPrincipal Cliente cliente){
		var cartao = cartaoService.criar(cliente, idConta, cartaoACriar);
		return ResponseEntity.ok(new CartaoCreditoResDto(cartao));
	}
	
	@GetMapping("/{idCartao}")
	public ResponseEntity<CartaoCreditoResDto> buscar(@PathVariable Long idCartao){
		var cartao = cartaoService.buscar(idCartao);
		return null;
				//ResponseEntity.ok(new CartaoResDto(cartao));
	}
	
	@PutMapping("/{idCartao}/limite")
	public ResponseEntity<CartaoCreditoResDto> alterarLimiteCredito(@PathVariable Long idCartao, @RequestBody @Valid NovoLimiteDto limite){
		var cartao = cartaoService.alterarLimiteCredito(idCartao, limite.novoLimite());
		
		return ResponseEntity.ok(new CartaoCreditoResDto(cartao));
	}
	
	@PutMapping("/{idCartao}/status ")
	public ResponseEntity<CartaoCreditoResDto> alterarStatus(@PathVariable Long idCartao){
		var cartao = cartaoService.alterarStatus(idCartao);
		
		return null;
		//ResponseEntity.ok(new CartaoResDto(cartao));
	}
	
	@PutMapping("/{idCartao}/senha ")
	public ResponseEntity<CartaoCreditoResDto> alterarSenha(@PathVariable Long idCartao, @RequestBody @Valid NovaSenhaDto senha){
		var cartao = cartaoService.alterarSenha(idCartao, senha.novaSenha());
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/limite-diario")
	public ResponseEntity<CartaoDebitoResDto> alterarLimiteDiario(@PathVariable Long idCartao, @RequestBody @Valid NovoLimiteDto limite){
		var cartao = cartaoService.alterarLimiteDiario(idCartao, limite.novoLimite());
		
		return ResponseEntity.ok(new CartaoDebitoResDto(cartao));
	}
	

}
