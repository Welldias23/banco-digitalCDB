package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.TransferenciaReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.TransferenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
	
	@Autowired
	private TransferenciaService transferenciaService;

	@PostMapping("/{idConta}")
	public ResponseEntity<TransferenciaResDto> transferir(@PathVariable Long idConta,
			@RequestBody @Valid TransferenciaReqDto transferenciaAFazer, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var transferencia = transferenciaService.transferir(clienteLogado, idConta, transferenciaAFazer);
		
		return ResponseEntity.ok(new TransferenciaResDto(transferencia));
	}
}
