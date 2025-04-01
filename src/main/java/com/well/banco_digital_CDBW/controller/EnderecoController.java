package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.EnderecoReqDto;
import com.well.banco_digital_CDBW.dto.EnderecoResDto;
import com.well.banco_digital_CDBW.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<EnderecoResDto> cadastrar(@RequestBody @Valid EnderecoReqDto enderecoReq) {
		var endereco = enderecoService.cadastrar(enderecoReq);
		
		return ResponseEntity.created(null).body(new EnderecoResDto(endereco));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoResDto> detalhar(@PathVariable Long id){
		var endereco = enderecoService.detalhar(id);
		
		return ResponseEntity.ok(new EnderecoResDto(endereco));
	}
}
