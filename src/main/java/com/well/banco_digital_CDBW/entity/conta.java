package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class conta {
	private Long id;
	private CategoriaCliente tipoConta;
	private BigDecimal saldo;
	private Cliente cliente;
	private Boolean ativa;
	
	
}
