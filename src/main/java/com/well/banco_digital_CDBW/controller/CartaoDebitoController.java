package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.NovoLimiteDto;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.service.CartaoDebitoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cartao-debito")
public class CartaoDebitoController {
	
	private final CartaoDebitoService CartaoDebitoService; 
	
	public CartaoDebitoController(CartaoDebitoService cartaoDebitoService) {
		this.CartaoDebitoService = cartaoDebitoService;
	}
	
	@PutMapping("/{idCartao}/limite-diario")
	@Operation(summary = "Altera o limite do cartao de debito", description = "Altera o limite do cartao de debito pelo id")
	@ApiResponse(responseCode = "200", 
		description = "Limite diario alterado", 
		content = @Content(schema = @Schema(implementation = Void.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "400", description = "Cartao não existe",
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
    )
	public ResponseEntity<Void> alterarLimiteDiario(@PathVariable Long idCartao, 
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody @Valid NovoLimiteDto limite){
		CartaoDebitoService.alterarLimiteDiario(idCartao, clienteLogado, limite.novoLimite());
		
		return ResponseEntity.ok().build();
	}

}
