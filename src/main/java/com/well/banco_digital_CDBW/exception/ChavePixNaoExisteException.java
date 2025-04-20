package com.well.banco_digital_CDBW.exception;

public class ChavePixNaoExisteException extends RuntimeException{
	public ChavePixNaoExisteException() {
		super("Chave pix não existe.");
	}
}
