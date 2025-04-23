package com.well.banco_digital_CDBW.exception;

public class TipoCartaoInvalidoException extends RuntimeException {
	public TipoCartaoInvalidoException() {
		super("Tipo de cartao invalido.");
	}
}
