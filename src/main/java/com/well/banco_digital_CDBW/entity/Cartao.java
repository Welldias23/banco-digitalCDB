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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private String numeroCartao;
	private String senha;
	private LocalDate dataCriacao;
	private LocalTime horaCriacao;
	private Boolean ativo;
	@ManyToOne
	private Conta conta;
	@OneToMany(mappedBy = "cartao")
	private List<Pagamento> compras;
	

	public void mudarSenha(String senha) {
		this.senha = senha;
		
	}

	public Cartao(Conta conta, String senha,  String numeroCartao) {
		this.numeroCartao =  numeroCartao;
		this.senha = senha;
		this.dataCriacao = LocalDate.now();
		this.horaCriacao = LocalTime.now();
		this.ativo = true;
		this.conta = (Conta) conta;
	}

	public Cartao(Conta conta, String numeroCartao) {
		this.numeroCartao = numeroCartao;
		this.dataCriacao = LocalDate.now();
		this.horaCriacao = LocalTime.now();
		this.ativo = true;
		this.conta = (Conta) conta;
	}

	public void ativarStatus() {
		this.ativo = true;
		
	}

	public void desativarStatus() {
		this.ativo = false;
		
	}

}
