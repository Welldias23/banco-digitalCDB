package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.PagamentoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cartao")
@Tag(name = "cartao", description = "Controlador para efetuar pagamento com cartao")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class PagamentoCartaoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping("/{idCartao}/pagamento")
	public ResponseEntity<PagamentoResDto> realizarPagamento(@RequestBody @Valid PagamentoReqDto pagamentoReq, 
			@AuthenticationPrincipal Cliente clienteLogado,
			@PathVariable Long idCartao){
		var pagamento = pagamentoService.pagar(idCartao, clienteLogado, pagamentoReq);
		
		return ResponseEntity.ok(pagamento);
	}
}
