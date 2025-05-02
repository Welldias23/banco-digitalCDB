package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoReqDto(
		@NotBlank(message = "O tipo do pagamento deve ser informado.")
		String tipoPagamento,
		@NotBlank(message = "O nome do estabelecimento deve ser informado no pagamento.")
		String nomeEstabelecimento,
		String nomeObjeto,
		@NotNull(message = "O valor deve ser informado no pagamento.")
		BigDecimal valor) {

}
