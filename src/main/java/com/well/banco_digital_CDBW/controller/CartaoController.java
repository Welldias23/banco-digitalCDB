package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.CartaoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.CartaoService;


@RestController
@RequestMapping("/cartao")
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService; 
	
	@PostMapping("/{idConta}")
	public ResponseEntity<CartaoResDto> criarCartaoCredito(@PathVariable Long idConta, @AuthenticationPrincipal Cliente cliente){
		var cartao = cartaoService.criar(cliente, idConta);
		return ResponseEntity.ok(new CartaoResDto(cartao));
	}

}
