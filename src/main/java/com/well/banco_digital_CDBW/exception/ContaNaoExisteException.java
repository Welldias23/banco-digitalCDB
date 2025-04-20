package com.well.banco_digital_CDBW.exception;

public class ContaNaoExisteException extends RuntimeException{
	public ContaNaoExisteException() {
		super("A conta não existe.");
	}
}
