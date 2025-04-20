package com.well.banco_digital_CDBW.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.exception.TokenInvalidoExcepition;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}") 
	private String secret;
	
	public String gerarToken(Cliente cliente) {

		try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API banco-digital-CDBW")
                    .withSubject(cliente.getId().toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
		} catch (JWTVerificationException ex) {
        	System.out.println(ex.toString());
			throw new TokenInvalidoExcepition();
		}
	}
	
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API banco-digital-CDBW")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException ex) {
        	throw new TokenInvalidoExcepition();
        }
    }
	
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
