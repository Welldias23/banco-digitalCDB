package com.well.banco_digital_CDBW.exception;

public class CriarContaException extends RuntimeException {
	public CriarContaException () {
		super("Erro ao criar conta.");
	}
}
