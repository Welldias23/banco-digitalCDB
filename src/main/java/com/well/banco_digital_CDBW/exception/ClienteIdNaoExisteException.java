package com.well.banco_digital_CDBW.exception;

public class ClienteIdNaoExisteException extends RuntimeException {
	public ClienteIdNaoExisteException() {
		super("Cliente não existe");
	}
}
