package com.well.banco_digital_CDBW.exception;


public class ClienteJaPossuiEnderecoException extends RuntimeException {

	public ClienteJaPossuiEnderecoException() {
		super("Cliente ja possui um endereco.");
	}

}
