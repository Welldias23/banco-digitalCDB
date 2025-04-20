package com.well.banco_digital_CDBW.exception;

public class ChavePixJaExisteException extends RuntimeException{
	public ChavePixJaExisteException() {
		super("A chave pix ja existe.");
	}
}
