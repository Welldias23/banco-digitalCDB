package com.well.banco_digital_CDBW.exception;

public class MenorDeIdadeException extends RuntimeException {	
	public MenorDeIdadeException () {
		super("Cliente menor de idade.");
	}
}
