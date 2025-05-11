package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.TransferenciaPixReqDto;
import com.well.banco_digital_CDBW.dto.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.TransferenciaDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.PixService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
@Tag(name = "pix", description = "Controlador para efetuar pix entre contas")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class PixController {
	
	private final PixService pixService;
	
	public PixController(PixService pixService) {
		this.pixService = pixService;
	}  
	
	@PostMapping("/{idConta}/pix")
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Realiar pix", description = "Realizar pix entre contas")
	@ApiResponse(responseCode = "201",
		description = "Pix realizado",
		content = @Content(schema = @Schema(implementation = TransferenciaDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos", 
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "422", description = "Saldo induficiente", 
		content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<TransferenciaDto> pix(@PathVariable Long idConta, 
			@RequestBody @Valid TransferenciaPixReqDto transferenciaPixAFazer,
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(pixService.transferir(clienteLogado, idConta, transferenciaPixAFazer));
	}

}
