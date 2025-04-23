package com.well.banco_digital_CDBW.exception;

public class LimiteDiarioInsuficiente extends RuntimeException {
	public LimiteDiarioInsuficiente() {
		super("Limite diario excedido.");
	}
}
