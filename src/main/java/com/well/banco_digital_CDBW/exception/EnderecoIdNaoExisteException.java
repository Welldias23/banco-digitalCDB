package com.well.banco_digital_CDBW.exception;

public class EnderecoIdNaoExisteException extends RuntimeException{
	public EnderecoIdNaoExisteException() {
		super("Endereco id não existe.");
	}
}
