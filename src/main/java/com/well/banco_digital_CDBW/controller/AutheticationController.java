package com.well.banco_digital_CDBW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.well.banco_digital_CDBW.dto.LoginDto;
import com.well.banco_digital_CDBW.dto.TokenJWTDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.security.SecurityConfigurations;
import com.well.banco_digital_CDBW.service.TokenService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "login cliente", description = "Controlador para cliente login")
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
public class AutheticationController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired 
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenJWTDto> longin(@RequestBody @Valid LoginDto login) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(login.cpf(), login.senha());
		var authetication = manager.authenticate(authenticationToken);
		var tokenJWT = tokenService.gerarToken((Cliente) authetication.getPrincipal());
		
		return ResponseEntity.ok(new TokenJWTDto(tokenJWT));
	}
}
