package com.well.banco_digital_CDBW.exception;

public class CpfJaExistenteException extends RuntimeException {
    public CpfJaExistenteException() {
        super("CPF já cadastrado");
    }
}
