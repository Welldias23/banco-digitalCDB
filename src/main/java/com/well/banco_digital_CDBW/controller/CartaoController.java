package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.CartaoReqDto;
import com.well.banco_digital_CDBW.dto.CartaoResDto;
import com.well.banco_digital_CDBW.dto.FaturaDto;
import com.well.banco_digital_CDBW.dto.FaturaPaga;
import com.well.banco_digital_CDBW.dto.NovaSenhaDto;
import com.well.banco_digital_CDBW.dto.NovoLimiteDto;
import com.well.banco_digital_CDBW.dto.PagamentoFatura;
import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.CartaoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cartao")
@Tag(name = "cartao", description = "Controlador para salvar e editar dados do cartao")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService; 
	
	@PostMapping("/{idConta}/emitir")
	public ResponseEntity<CartaoResDto> criarCartaoCredito(@RequestBody CartaoReqDto cartaoACriar, 
			@PathVariable Long idConta, 
			@AuthenticationPrincipal Cliente cliente){
		var cartao = cartaoService.criar(cliente, idConta, cartaoACriar);
		return ResponseEntity.ok(cartao);
	}
	
	@GetMapping("/{idCartao}")
	public ResponseEntity<CartaoResDto> buscar(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoService.detalhar(idCartao, clienteLogado);
		return ResponseEntity.ok(cartao);
	}
	
	@PutMapping("/{idCartao}/limite")
	public ResponseEntity<CartaoResDto> alterarLimiteCredito(@PathVariable Long idCartao, 
			@RequestBody @Valid NovoLimiteDto limite, 
			@AuthenticationPrincipal Cliente clienteLogado){
		var cartao = cartaoService.alterarLimiteCredito(idCartao, clienteLogado, limite.novoLimite());
		
		return ResponseEntity.ok(cartao);
	}
	
	@PutMapping("/{idCartao}/ativar")
	public ResponseEntity<Void> ativarStatus(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.ativarStatus(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/desativar")
	public ResponseEntity<Void> desativarStatus(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		cartaoService.desativarStatus(idCartao, clienteLogado);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/senha")
	public ResponseEntity<Void> alterarSenha(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody @Valid NovaSenhaDto senha){
		cartaoService.alterarSenha(idCartao, clienteLogado, senha.novaSenha());
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idCartao}/limite-diario")
	public ResponseEntity<Void> alterarLimiteDiario(@PathVariable Long idCartao, 
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody @Valid NovoLimiteDto limite){
		cartaoService.alterarLimiteDiario(idCartao, clienteLogado, limite.novoLimite());
		
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{idCartao}/fatura")
	public ResponseEntity<FaturaDto> consultarFatura(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado){
		var fatuta = cartaoService.consultarFatura(idCartao, clienteLogado);
		return ResponseEntity.ok(fatuta);
	}
	
	@PostMapping("/{idCartao}/fatura/pagamento")
	public ResponseEntity<FaturaPaga> pagarFatura(@PathVariable Long idCartao,
			@AuthenticationPrincipal Cliente clienteLogado,
			@RequestBody PagamentoFatura pagamentoFatura){
		var faturaPaga = cartaoService.pagarFatura(idCartao, clienteLogado, pagamentoFatura);
		
		return ResponseEntity.ok(faturaPaga);
	}

}
