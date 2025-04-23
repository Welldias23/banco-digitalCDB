package com.well.banco_digital_CDBW.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.well.banco_digital_CDBW.exception.TokenInvalidoExcepition;
import com.well.banco_digital_CDBW.service.ClienteService;
import com.well.banco_digital_CDBW.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	@Lazy
	private TokenService tokenService;
	
	@Autowired
	@Lazy
	private ClienteService clienteService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var tokenJWT = recuperarToken(request);

		if(tokenJWT != null) {
			var clienteId = tokenService.getSubject(tokenJWT);
			var cliente = clienteService.buscarclientePorId(Long.parseLong(clienteId));
			var authetication = new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authetication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}

}
