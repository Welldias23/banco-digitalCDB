package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

public record FaturaPaga(
		BigDecimal valorFatura,
		BigDecimal valorPago,
		BigDecimal limiteDisponivel) {

}
