package com.well.banco_digital_CDBW.exception;

public class CategoriaNaoExisteException extends RuntimeException{
	public CategoriaNaoExisteException() {
		super("Nao existe uma categoria.");
	}
}
