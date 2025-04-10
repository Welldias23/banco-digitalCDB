package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "CompraDetalhes")
@Table(name = "compras")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompraDetalhes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeObjeto;
	private LocalDate data;
	private LocalTime hora;
	private BigDecimal valor;
	@OneToOne
	private Cartao cartao;
	
}
