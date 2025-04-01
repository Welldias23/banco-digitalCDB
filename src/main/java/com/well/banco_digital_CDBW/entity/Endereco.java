package com.well.banco_digital_CDBW.entity;

import com.well.banco_digital_CDBW.dto.EnderecoReqDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	//receber os dados e verificar o cep em outra api
	private String cidade;
	private String estado;
	private String rua; 
	private Integer numero;
	private String complemento;
	private String bairro;

	
	public Endereco(EnderecoReqDto endereco) {
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.estado = endereco.estado();
		this.rua = endereco.rua();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.bairro = endereco.bairro();
	}
}

