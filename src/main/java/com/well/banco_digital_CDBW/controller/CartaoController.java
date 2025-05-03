package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.dto.NovaSenhaDto;
import com.well.banco_digital_CDBW.dto.RespostaDeErros;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.CartaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cartao")
@Tag(name = "cartao", description = "Controlador para salvar e editar dados do cartao")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class CartaoController {
	
	private CartaoService cartaoService; 
	
	public CartaoController(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}
	
	@GetMapping("/{idCartao}")
	@JsonView(View.Detalhar.class)
	@Operation(summary = "Detalha um cartao", description = "Detalha um cartao pelo id, e que pertenca ao cliente logado")
	@ApiResponse(responseCode = "200", 
			description = "Cartao retornado",
			content = @Content(schema = @Schema(implementation = CartaoDto.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "400", description = "Cartao não existe",
	content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
    )
	public ResponseEntity<CartaoDto> buscarCartao(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoService.detalharCartao(idCartao, clienteLogado);
		return ResponseEntity.ok(cartao);
	}
	
	
	@PutMapping("/{idCartao}/ativar")
	@Operation(summary = "Ativa um cartao", description = "Ativa um cartao pelo id")
	@ApiResponse(responseCode = "200", 
			description = "Cartao ativado",
			content = @Content(schema = @Schema(implementation = Void.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "400", description = "Cartao não existe",
	content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
    )
	public ResponseEntity<Void> ativarCartao(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.ativarStatusCartao(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/desativar")
	@Operation(summary = "Desativa um cartao", description = "Desativa um cartao pelo id")
	@ApiResponse(responseCode = "200", 
			description = "Cartao desativado",
			content = @Content(schema = @Schema(implementation = Void.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "400", description = "Cartao não existe",
	content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
    )
	public ResponseEntity<Void> desativarCartao(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.desativarStatusCartao(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/senha")
	@Operation(summary = "Altera senha do cartao", description = "Altera senha do cartao pelo id")
	@ApiResponse(responseCode = "200", 
			description = "Senha alterada",
			content = @Content(schema = @Schema(implementation = Void.class))
	)
	@ApiResponse(responseCode = "400", description = "Dados invalidos",
			content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
	)
	@ApiResponse(responseCode = "400", description = "Cartao não existe",
	content = @Content(schema = @Schema(implementation = RespostaDeErros.class))
    )
	public ResponseEntity<Void> alterarSenhaCartao(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody @Valid NovaSenhaDto senha){
		cartaoService.alterarSenhaCartao(idCartao, clienteLogado, senha.novaSenha());
		
		return ResponseEntity.ok().build();
	}
	

}
