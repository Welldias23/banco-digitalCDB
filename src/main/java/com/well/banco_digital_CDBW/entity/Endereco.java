package com.well.banco_digital_CDBW.entity;

import java.util.Optional;

import com.well.banco_digital_CDBW.dto.EnderecoDto;

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

	
	public Endereco(EnderecoDto endereco, Cliente cliente) {
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.estado = endereco.estado();
		this.rua = endereco.rua();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.bairro = endereco.bairro();
		this.cliente = cliente;
	}


	public void atualizarCliente(EnderecoDto endereco) {
		Optional.ofNullable(endereco.cep())
				.ifPresent(cep -> this.cep = cep);
		Optional.ofNullable(endereco.cidade())
				.ifPresent(cidade -> this.cidade = cidade); 
		Optional.ofNullable(endereco.estado())
				.ifPresent(estado -> this.estado = estado); 
	    Optional.ofNullable(endereco.rua())
	    		.ifPresent(rua -> this.rua = rua);	
		Optional.ofNullable(endereco.numero())
				.ifPresent(numero -> this.numero = numero); 
		Optional.ofNullable(endereco.complemento())
				.ifPresent(complemento -> this.complemento = complemento); 
		Optional.ofNullable(endereco.bairro())
				.ifPresent(bairro -> this.bairro = bairro); 
	}

}

