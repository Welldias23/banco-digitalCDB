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
import com.well.banco_digital_CDBW.dto.SaqueDto;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.SaqueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/saque")
@Tag(name = "saque", description = "Controlador para efetuar saque de uma conta")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class SaqueController {
	
	private final SaqueService saqueService;
	
	public SaqueController(SaqueService saqueService) {
		this.saqueService = saqueService;
	}
	
	@PostMapping("/{idConta}")
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Sacar da conta", description = "Saca da conta desejada do cliente logado")
	@ApiResponse(responseCode = "201",
			description = "Saque realizado",
			content = @Content(schema = @Schema(implementation = SaqueDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "422", description = "Saldo insuficiente",
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<SaqueDto> sacar(@RequestBody @Validated(Creat.class) SaqueDto saqueAFazer,
			@PathVariable Long idConta,
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(saqueService.sacar(clienteLogado, idConta, saqueAFazer));
	}
	
	

}
