package com.well.banco_digital_CDBW.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Cartao")
@Table(name = "cartoes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numeroCartao;
	private LocalDate dataCriacao;
	private LocalTime horaCriacao;
	private Boolean ativo;
	@OneToOne
	private Conta conta;
	@OneToMany
	private List<CompraDetalhes> compras;
	
	public Cartao(Object conta) {
		//criar uma logica para criar o numero do cartao
		this.dataCriacao = LocalDate.now();
		this.horaCriacao = LocalTime.now();
		this.ativo = true;
		this.conta = (Conta) conta;
	}

}
