package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.Creat;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.TransferenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/conta")
@Tag(name = "transferencia", description = "Controlador para efetuar trasnferencia entre contas")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class TransferenciaController {
	
	private final TransferenciaService transferenciaService;
	
	public TransferenciaController(TransferenciaService transferenciaService) {
		this.transferenciaService = transferenciaService;
	}

	@PostMapping("/{idConta}/transferencia")
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Realizar transferencia", description = "Realiza transferencia entre contas")
	@ApiResponse(responseCode = "201", 
		description = "Transferencia relizada",
		content = @Content(schema = @Schema(implementation = TransferenciaDto.class))		
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "422", description = "Saldo insuficiente",
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<TransferenciaDto> transferir(@PathVariable Long idConta,
			@RequestBody @Validated(Creat.class) TransferenciaDto transferenciaAFazer, 
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(transferenciaService.transferir(clienteLogado, idConta, transferenciaAFazer));
	}
}
