package com.well.banco_digital_CDBW.exception;

public class TokenInvalidoExcepition extends RuntimeException {
	public TokenInvalidoExcepition() {
		super("Token JWT inválido ou expirado.");
	}
}
