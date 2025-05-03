package com.well.banco_digital_CDBW.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.dto.PagamentoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pagamento")
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Pagamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeEstabelecimento;
	private String nomeObjeto;
	private BigDecimal valor;
	private LocalDate data;
	private LocalTime hora;
	
	public Pagamento(PagamentoDto pagamentoReq) {
		this.nomeEstabelecimento = pagamentoReq.nomeEstabelecimento();
		this.nomeObjeto = pagamentoReq.nomeObjeto();
		this.valor = pagamentoReq.valor();
		this.data = LocalDate.now();
		this.hora = LocalTime.now();
	}
	
	public abstract void pagar();
	
}
