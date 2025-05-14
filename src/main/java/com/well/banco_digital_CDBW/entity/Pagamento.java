package com.well.banco_digital_CDBW.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.well.banco_digital_CDBW.dto.PagamentoDto;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
public abstract class Pagamento{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeEstabelecimento;
	private String nomeObjeto;
	private BigDecimal valor;
	private LocalDate dataPagamento ;
	private LocalTime horaPagamento ;
	
	public Pagamento(PagamentoDto pagamentoReq) {
		this.nomeEstabelecimento = pagamentoReq.nomeEstabelecimento();
		this.nomeObjeto = pagamentoReq.nomeObjeto();
		this.valor = pagamentoReq.valor();
		this.dataPagamento = LocalDate.now();
		this.horaPagamento = LocalTime.now();
	}
	
	public abstract void pagar();
	
}
