package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Transacao")
@Table(name = "transacoes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "conta_destino_id")
	private Conta contaDestino;
	private String nomeDestino;
	@ManyToOne
	@JoinColumn(name = "conta_origem_id")
	private Conta contaOrigem;
	private String nomeOrigem;
	private BigDecimal valor;
	private LocalDate data;
	private LocalTime horario;


	public Transacao(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		this.contaOrigem = contaOrigem;
		contaOrigem.debitar(valor);
		this.contaDestino = contaDestino;	
		contaDestino.creditar(valor);
		this.valor = valor;
		this.data = LocalDate.now();
		this.horario = LocalTime.now();
		
	}


	public Transacao(BigDecimal valor) {
		this.valor = valor;
		this.data = LocalDate.now();
		this.horario = LocalTime.now();
	}

	
	public Transacao(Conta contaDestino, BigDecimal valor) {
		this.contaDestino = contaDestino;
		contaDestino.creditar(valor);
		this.valor = valor;
		this.data = LocalDate.now();
		this.horario = LocalTime.now();
	}


}
