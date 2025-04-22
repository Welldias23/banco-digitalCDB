package com.well.banco_digital_CDBW.exception;

public class LimiteDeCreditoInsuficiente extends RuntimeException{
	public LimiteDeCreditoInsuficiente () {
		super("Limite insuficiente.");
	}
}
