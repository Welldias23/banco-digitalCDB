package com.well.banco_digital_CDBW.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;
import com.well.banco_digital_CDBW.entity.Pagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDto(
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		Long id,
		
		@JsonView(View.Persistir.class)
		@NotBlank(groups = Complete.class, message = "O tipo do pagamento deve ser informado.")
		String tipoPagamento,
		
		@JsonView(View.Persistir.class)
		@NotNull(groups = Complete.class,message = "O id do cartao ou da conta que sera enviado o pix é obrigatorio.")
		Long idDaFormaDePagamento,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		@NotBlank(groups = Complete.class,message = "O nome do estabelecimento deve ser informado no pagamento.")
		String nomeEstabelecimento,
		
		@JsonView({View.Detalhar.class, View.Persistir.class})
		String nomeObjeto,
		
		@JsonView(View.Detalhar.class)
		@NotNull(groups = Complete.class,message = "O valor deve ser informado no pagamento.")
		BigDecimal valor,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalDate data,
		
		@JsonView(View.Detalhar.class)
		@Schema(accessMode = Schema.AccessMode.READ_ONLY)
		LocalTime hora) {
	public PagamentoDto(Pagamento pagamento) {
		this(
				pagamento.getId(),
				null,
				null,
				pagamento.getNomeEstabelecimento(), 
				pagamento.getNomeObjeto(), 
				pagamento.getValor(), 
				pagamento.getData(), 
				pagamento.getHora());
	}

}
