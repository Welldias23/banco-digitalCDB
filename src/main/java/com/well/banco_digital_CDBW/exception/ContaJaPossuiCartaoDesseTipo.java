package com.well.banco_digital_CDBW.exception;

public class ContaJaPossuiCartaoDesseTipo extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaJaPossuiCartaoDesseTipo() {
		super("Conta ja possui um cartao desse tipo.");
	}

}
