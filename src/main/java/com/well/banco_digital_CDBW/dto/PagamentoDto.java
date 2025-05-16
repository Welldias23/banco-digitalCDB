package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDto(
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Long id,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Creat.class, message = "O tipo do pagamento deve ser informado: cartao de debito ou cartao de credito.")
		String tipoPagamento,
		
		@JsonView(View.Persistir.class)
		@NotNull(groups = Creat.class,message = "O id do cartao.")
		Long idDoCartao,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Creat.class,message = "O nome do estabelecimento deve ser informado no pagamento.")
		String nomeEstabelecimento,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		String nomeObjeto,
		
		@JsonView(View.Detalhar.class)
		@NotNull(groups = Creat.class,message = "O valor deve ser informado no pagamento.")
		BigDecimal valor,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalDate data,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalTime hora) {
}
