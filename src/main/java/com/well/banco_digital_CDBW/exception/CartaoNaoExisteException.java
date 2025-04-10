package com.well.banco_digital_CDBW.exception;

public class CartaoNaoExisteException extends RuntimeException {
	public CartaoNaoExisteException() {
		super("O cartão não existe.");
	}
}
