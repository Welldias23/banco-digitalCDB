package com.well.banco_digital_CDBW.entity;

import java.util.Optional;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Endereco")
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cep;
	private String cidade;
	private String estado;
	private String rua; 
	private Integer numero;
	private String complemento;
	private String bairro;
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

	
	public Endereco(Endereco endereco, Cliente cliente) {
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.bairro = endereco.getBairro();
		this.cliente = cliente;
	}


	public void atualizarCliente(Endereco endereco) {
		Optional.ofNullable(endereco.getCep())
				.ifPresent(cep -> this.cep = cep);
		Optional.ofNullable(endereco.getCidade())
				.ifPresent(cidade -> this.cidade = cidade); 
		Optional.ofNullable(endereco.getEstado())
				.ifPresent(estado -> this.estado = estado); 
	    Optional.ofNullable(endereco.getRua())
	    		.ifPresent(rua -> this.rua = rua);	
		Optional.ofNullable(endereco.getNumero())
				.ifPresent(numero -> this.numero = numero); 
		Optional.ofNullable(endereco.getComplemento())
				.ifPresent(complemento -> this.complemento = complemento); 
		Optional.ofNullable(endereco.getBairro())
				.ifPresent(bairro -> this.bairro = bairro); 
	}

}

