package com.well.banco_digital_CDBW.exception;

public class SaldoInsuficienteException extends RuntimeException{
	public SaldoInsuficienteException() {
		super("Saldo insuficiente.");
	}
}
