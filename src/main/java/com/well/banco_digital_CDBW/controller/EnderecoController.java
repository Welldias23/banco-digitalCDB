package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.Complete;
import com.well.banco_digital_CDBW.dto.EnderecoDto;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/endereco")
@Tag(name = "endereco", description = "Controlador para salvar e editar dados do endereco")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class EnderecoController {
	
	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@PostMapping
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Cadastrar endereco", description = "Cria um novo endereco")
	@ApiResponse(responseCode = "201", 
		description = "Endereco cadastrado", 
		content = @Content(schema = @Schema(implementation = EnderecoDto.class))
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
	public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody @Validated(Complete.class) EnderecoDto enderecoReq, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		return ResponseEntity.created(null).body(enderecoService.cadastrarCliente(enderecoReq, clienteLogado));
	}
	
	@GetMapping
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Detalhar endereco", description = "Detalha endereco do cliente logado")
	@ApiResponse(responseCode = "200", 
		description = "Endereco detalhado", 
		content = @Content(schema = @Schema(implementation = EnderecoDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<EnderecoDto> detalharEndereco(@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(enderecoService.detalharEndereco(clienteLogado));
	}
	
	@PutMapping
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Atualizar endereco", description = "Atualiza o endereco do cliente logado")
	@ApiResponse(responseCode = "201", 
		description = "Endereco atualizado", 
		content = @Content(schema = @Schema(implementation = EnderecoDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<EnderecoDto> atualizarEndereco(@RequestBody @Validated(Complete.class) EnderecoDto enderecoAtualizar,
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(enderecoService.atualizarEndereco(clienteLogado, enderecoAtualizar));
	}
	
	@PatchMapping
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Atualizar endereco parcialmente", 
	description = "atualiza o endereco do cliente logado parcialmente"
	)
	@ApiResponse(responseCode = "201", 
		description = "Endereco atualizado", 
		content = @Content(schema = @Schema(implementation = EnderecoDto.class))
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
	public ResponseEntity<EnderecoDto> atualizarParcialmenteEndereco(@RequestBody @Validated EnderecoDto enderecoAtualizar,
			@AuthenticationPrincipal Cliente clienteLogado){
		
		return ResponseEntity.ok(enderecoService.atualizarEndereco(clienteLogado, enderecoAtualizar));
	}
	
	@DeleteMapping
	@Operation(summary = "Excluir endereco", description = "Excluir endereco do cliente logado")
	@ApiResponse(responseCode = "204", description = "Endereco excluido")
	public ResponseEntity<Void> excluirEndereco(@AuthenticationPrincipal Cliente clienteLogado){
		enderecoService.excluirEndereco(clienteLogado);
		
		return ResponseEntity.noContent().build();
	}
}
