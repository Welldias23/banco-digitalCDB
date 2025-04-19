package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.SaqueReqDto;
import com.well.banco_digital_CDBW.dto.SaqueResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.SaqueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/saque")
public class SaqueController {
	
	@Autowired
	private SaqueService saqueService;
	
	
	@PostMapping("/{idConta}")
	public ResponseEntity<SaqueResDto> sacar(@RequestBody @Valid SaqueReqDto saqueAFazer,
			@PathVariable Long idConta,
			@AuthenticationPrincipal Cliente clienteLogado){
		var saque = saqueService.sacar(clienteLogado, idConta, saqueAFazer);
		
		return ResponseEntity.ok(new SaqueResDto(saque));
	}
	
	

}
