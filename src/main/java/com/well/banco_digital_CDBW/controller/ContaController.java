package com.well.banco_digital_CDBW.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.ContaDto;
import com.well.banco_digital_CDBW.dto.PixDto;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.SaldoDto;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
@Tag(name = "contas", description = "Controlador para salvar e editar dados da conta")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class ContaController {
	
	private final ContaService contaService;
	
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	

	@PostMapping
	@JsonView(View.Get.class)
	@Operation(summary = "Criar conta", description = "Cria uma nova conta")
	@ApiResponse(responseCode = "201", 
		description = "Conta criada", 
		content = @Content(schema = @Schema(implementation = ContaDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "409", description = "CPF ou email já cadastrado", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "422", description = "Cliente menor de idade", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<ContaDto> cadastrarConta( @RequestBody @Validated ContaDto contaAAbrir, 
			@AuthenticationPrincipal Cliente clienteLogado){
		ContaDto conta = contaService.criarConta(clienteLogado.getId(), contaAAbrir);
		
		return ResponseEntity.ok(conta);	
	}
	
	@PostMapping("/pix/{idConta}")
	@JsonView(View.Get.class)
	@Operation(summary = "Criar pix na conta", description = "Cria uma chave pix na conta e valida se ela é unica")
	@ApiResponse(responseCode = "201", 
		description = "Pix criado", 
		content = @Content(schema = @Schema(implementation = ContaDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "409", description = "Chave pix já cadastrada", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<ContaDto> cadastrarPixConta(@PathVariable Long idConta,
			@RequestBody @Valid PixDto pix, 
			@AuthenticationPrincipal Cliente clienteLogado){
		ContaDto conta = contaService.cadastrarPixConta(idConta, clienteLogado.getId(), pix);
		return ResponseEntity.ok(conta);
	}
	
	@GetMapping("/{idConta}")
	@JsonView(View.Get.class)
	@Operation(summary = "Detalhar conta", description = "Detalha conta por id")
	@ApiResponse(responseCode = "200", 
		description = "Conta detalhada", 
		content = @Content(schema = @Schema(implementation = ContaDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<ContaDto> detalharUmaConta(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ContaDto conta = contaService.detalharConta(idConta, clienteLogado.getId());
		return ResponseEntity.ok(conta);
	}
	
	@GetMapping("/saldo/{idConta}")
	@Operation(summary = "Saldo da conta", description = "Detalha saldo da conta por id")
	@ApiResponse(responseCode = "200", 
		description = "Saldo detalhado", 
		content = @Content(schema = @Schema(implementation = SaldoDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<SaldoDto> consultarSaldoConta(@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		SaldoDto saldo = contaService.buscarSaldoConta(idConta, clienteLogado.getId());
		
		return ResponseEntity.ok(saldo);
	}
	
	
}




















