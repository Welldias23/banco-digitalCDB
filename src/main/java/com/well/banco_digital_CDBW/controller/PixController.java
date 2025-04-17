package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.dto.TransferenciaResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.PixService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pix")
public class PixController {
	
	@Autowired
	private PixService pixService;
	
	@PostMapping("/{idConta}")
	public ResponseEntity<TransferenciaResDto> pix(@PathVariable Long idConta, 
			@RequestBody @Valid TransferenciaPixReqDto transferenciaPixAFazer,
			@AuthenticationPrincipal Cliente clienteLogado){
		var transferenciaPix = pixService.transferir(clienteLogado, idConta, transferenciaPixAFazer);
		
		return ResponseEntity.ok(new TransferenciaResDto(transferenciaPix));
	}

}
