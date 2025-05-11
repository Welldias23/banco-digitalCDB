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
import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.PixService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pix")
@Tag(name = "pix", description = "Controlador para efetuar pix entre contas")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class PixController {
	
	@Autowired
	private PixService pixService;
	
	@PostMapping("/{idConta}")
	public ResponseEntity<TransferenciaDto> pix(@PathVariable Long idConta, 
			@RequestBody @Valid TransferenciaPixReqDto transferenciaPixAFazer,
			@AuthenticationPrincipal Cliente clienteLogado){
		var transferenciaPix = pixService.transferir(clienteLogado, idConta, transferenciaPixAFazer);
		
		//return ResponseEntity.ok(new TransferenciaDto(transferenciaPix));
		return null;
	}

}
