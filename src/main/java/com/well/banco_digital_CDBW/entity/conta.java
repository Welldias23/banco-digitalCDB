package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Conta")
@Table(name = "contas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long agencia;
	private Long numeroConta;
	private BigDecimal saldo;
	private Boolean ativa;
	private LocalDate dataCriacao;
	private String chavePix;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToMany
    private List<Cartao> cartoes;
    @OneToMany(mappedBy = "contaOrigem")
    private List<Transferencia> transferenciasEnviadas;   
    @OneToMany(mappedBy = "contaDestino")
    private List<Transferencia> transferenciasRecebidas;

	
	public Conta(Cliente cliente) {
		this.agencia = new Long(1001);
		this.saldo = saldo.ZERO;
		this.ativa = true;
		this.dataCriacao = LocalDate.now();
		this.cliente = cliente;
		
	}

	public void gerarNumeroConta(Long id) {
		this.numeroConta = id + 5613;
	}

	public void debitar(BigDecimal valor) {
		this.saldo = saldo.subtract(valor);
	}


	public void creditar(BigDecimal valor) {
		this.saldo = saldo.add(valor);
	}
	
}
