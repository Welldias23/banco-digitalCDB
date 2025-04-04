package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

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

@Entity(name = "Transferencia")
@Table(name = "transferencias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transferencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Conta contaOrigem;
	private String nomeOrigem;
	@OneToOne
	private Conta contaDestino;
	private String nomeDestino;
	private BigDecimal valor;


	public Transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		contaOrigem.debitar(valor);
		contaDestino.creditar(valor);
		
	}


}
