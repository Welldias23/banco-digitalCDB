package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.Creat;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.dto.DepositoDto;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.DepositoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/conta")
@Tag(name = "deposito", description = "Controlador para efetuar deposito")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class DepositoController {
	
	private final DepositoService depositoService;
	
	public DepositoController(DepositoService depositoService) {
		this.depositoService = depositoService;
	} 
	
	@PostMapping("/deposito")
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Depositar em conta", description = "Depositar em uma conta valida")
	@ApiResponse(responseCode = "201",
		description = "Deposito realizado",
		content = @Content(schema = @Schema(implementation = DepositoDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "404", description = "Conta nao existe",
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<DepositoDto> depositar(@RequestBody @Validated(Creat.class) DepositoDto deposito, 
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(depositoService.depositar(clienteLogado, deposito));
	}

}
