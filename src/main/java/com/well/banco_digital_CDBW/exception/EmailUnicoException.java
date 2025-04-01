package com.well.banco_digital_CDBW.exception;

public class EmailUnicoException extends RuntimeException {
	public EmailUnicoException() {
		super("Email ja existe.");
	}
}
