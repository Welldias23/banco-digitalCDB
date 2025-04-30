package com.well.banco_digital_CDBW.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.dto.CartaoDto;
import com.well.banco_digital_CDBW.dto.FaturaDto;
import com.well.banco_digital_CDBW.dto.FaturaPaga;
import com.well.banco_digital_CDBW.dto.NovoLimiteDto;
import com.well.banco_digital_CDBW.dto.PagamentoFatura;
import com.well.banco_digital_CDBW.dto.View;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.CartaoCreditoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cartao-credito")
@Tag(name = "cartao credito", description = "Controlador para salvar e editar dados do cartao de credito")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class CartaoCreditoController {
	
	private final CartaoCreditoService cartaoCreditoService;
	
	public CartaoCreditoController(CartaoCreditoService cartaoCreditoService) {
		this.cartaoCreditoService = cartaoCreditoService;
	}
	
	@PostMapping("/{idConta}/emitir")
	@JsonView(View.Get.class)
	public ResponseEntity<CartaoDto> criarCartaoCredito(@RequestBody CartaoDto cartaoACriar, 
			@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente cliente){
		var cartao = cartaoCreditoService.criarCartaoCredito(cliente, idConta, cartaoACriar);
		return ResponseEntity.ok(cartao);
	}
	
	@PutMapping("/{idCartao}/limite")
	@JsonView(View.Get.class)
	public ResponseEntity<CartaoDto> alterarLimiteCredito(@PathVariable Long idCartao, 
			@RequestBody @Valid NovoLimiteDto limite, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoCreditoService.alterarLimiteCredito(idCartao, clienteLogado, limite.novoLimite());
		
		return ResponseEntity.ok(cartao);
	}
	
	
	@GetMapping("/{idCartao}/fatura")
	public ResponseEntity<FaturaDto> consultarFatura(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		var fatuta = cartaoCreditoService.consultarFatura(idCartao, clienteLogado);
		return ResponseEntity.ok(fatuta);
	}
	
	@PostMapping("/{idCartao}/fatura/pagamento")
	public ResponseEntity<FaturaPaga> pagarFatura(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody PagamentoFatura pagamentoFatura){
		var faturaPaga = cartaoCreditoService.pagarFatura(idCartao, clienteLogado, pagamentoFatura);
		
		return ResponseEntity.ok(faturaPaga);
	}

}
