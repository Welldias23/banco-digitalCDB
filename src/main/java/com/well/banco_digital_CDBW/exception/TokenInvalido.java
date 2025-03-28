package com.well.banco_digital_CDBW.exception;

public class TokenInvalido extends RuntimeException {
	public TokenInvalido() {
		super("Token JWT inválido ou expirado.");
	}
}
