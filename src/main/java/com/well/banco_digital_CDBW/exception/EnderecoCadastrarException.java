package com.well.banco_digital_CDBW.exception;

public class EnderecoCadastrarException extends RuntimeException {
	public EnderecoCadastrarException() {
		super("Cliente ja tem um endereco cadastrado.");
	}
}
