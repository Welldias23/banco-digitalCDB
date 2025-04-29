package com.well.banco_digital_CDBW.dto;

import java.time.LocalDateTime;

public record RespostaDeErros(
		    String type,
		    String title,
		    int status,
		    String detail,
		    String instance,
		    LocalDateTime TimesTemp) {

}
