package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.DepositoReqDto;
import com.well.banco_digital_CDBW.dto.DepositoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.DepositoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/deposito")
public class DepositoController {
	
	@Autowired
	private DepositoService depositoService;
	
	@PostMapping
	public ResponseEntity<DepositoResDto> depositar(@RequestBody @Valid DepositoReqDto deposito, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var depositoFeito = depositoService.depositar(clienteLogado, deposito);

		System.out.println(depositoFeito.getData());
		
		return ResponseEntity.ok(new DepositoResDto(depositoFeito));
	}

}
