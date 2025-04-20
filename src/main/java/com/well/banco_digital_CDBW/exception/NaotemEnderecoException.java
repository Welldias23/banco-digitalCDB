package com.well.banco_digital_CDBW.exception;

public class NaotemEnderecoException extends RuntimeException {
	public NaotemEnderecoException() {
		super("Não existe nenhum endereço cadastrado.");
	}
}
