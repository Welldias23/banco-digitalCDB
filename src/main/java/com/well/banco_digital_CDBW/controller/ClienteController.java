package com.well.banco_digital_CDBW.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.dto.Complete;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/cliente")
@Tag(name = "cliente", description = "Controlador para salvar e editar dados do cliente")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@PostMapping
	@JsonView(View.Get.class)
	@Operation(summary = "Cadastrar cliente", description = "Cria um novo cliente validando idade mínima, CPF e email")
	@ApiResponse(responseCode = "201", 
		description = "Cliente cadastrado", 
		content = @Content(schema = @Schema(implementation = ClienteDto.class))
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
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Validated(Complete.class) ClienteDto clienteReq, 
			UriComponentsBuilder uriBuilder){		
		ClienteDto cliente = clienteService.cadastrarCliente(clienteReq);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.id()).toUri();
		return ResponseEntity.created(uri).body(cliente);
		
	}
	
	@GetMapping
	@JsonView(View.Get.class)
	@Operation(summary = "Detalhar cliente", description = "Detalha o cliente logado")
	@ApiResponse(responseCode = "200", 
		description = "Cliente detalhado", 
		content = @Content(schema = @Schema(implementation = ClienteDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", 
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	public ResponseEntity<ClienteDto> detalharCliente(@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.detalharCliente(clienteLogado);
		return ResponseEntity.ok(cliente);	
	}
	
	@PutMapping
	@JsonView(View.Get.class)
	@Operation(summary = "Atualizar cliente", description = "Atualiza o cliente logado validando idade mínima, CPF e email")
	@ApiResponse(responseCode = "201", 
		description = "Cliente atualizado", 
		content = @Content(schema = @Schema(implementation = ClienteDto.class))
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
	public ResponseEntity<ClienteDto> atualizarCliente(@RequestBody @Validated(Complete.class) ClienteDto clienteAtualizar, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.atualizarCliente(clienteAtualizar, clienteLogado);
		return ResponseEntity.ok(cliente);
	}
	
	@PatchMapping
	@JsonView(View.Get.class)
	@Operation(summary = "Atualizar cliente parcialmente", 
		description = "atualiza o cliente logado parcialmente validando idade mínima, CPF e email"
	)
	@ApiResponse(responseCode = "201", 
		description = "Cliente atualizado", 
		content = @Content(schema = @Schema(implementation = ClienteDto.class))
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
	public ResponseEntity<ClienteDto> atualizarParcialmenteCliente(@RequestBody @Validated ClienteDto clienteAtualizar, 
			@AuthenticationPrincipal Cliente clienteLogado) {
		ClienteDto cliente = clienteService.atualizarCliente(clienteAtualizar, clienteLogado);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping
	@Operation(summary = "Excluir cliente", description = "Excluir cliente logado")
	@ApiResponse(responseCode = "204", description = "Cliente excluido")
	public ResponseEntity<ClienteDto> excluirCliente(@AuthenticationPrincipal Cliente clienteLogado) {
		clienteService.excluirCliente(clienteLogado);
		return ResponseEntity.noContent().build();
	}
	
}


















