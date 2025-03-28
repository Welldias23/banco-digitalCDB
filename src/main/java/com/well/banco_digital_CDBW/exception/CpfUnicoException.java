package com.well.banco_digital_CDBW.exception;

public class CpfUnicoException extends RuntimeException {
    public CpfUnicoException() {
        super("CPF já cadastrado");
    }
}
