package com.well.banco_digital_CDBW.entity;


import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("transferencia")
@Getter
@Setter
public class Transferencia extends Transacao{
	
	public Transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		super(contaOrigem, contaDestino, valor);

	}
	
}
