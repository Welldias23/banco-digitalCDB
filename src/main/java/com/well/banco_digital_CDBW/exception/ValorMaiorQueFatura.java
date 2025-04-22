package com.well.banco_digital_CDBW.exception;

public class ValorMaiorQueFatura extends RuntimeException {
	public ValorMaiorQueFatura() {
		super("O valor da fatura é menor.");
	}
}
