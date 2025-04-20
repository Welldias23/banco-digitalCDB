package com.well.banco_digital_CDBW.entity;

import com.well.banco_digital_CDBW.dto.EnderecoReqDto;

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
	
	//receber os dados e verificar o cep em outra api
	private String cidade;
	private String estado;
	private String rua; 
	private Integer numero;
	private String complemento;
	private String bairro;
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

	
	public Endereco(EnderecoReqDto endereco, Cliente cliente) {
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.estado = endereco.estado();
		this.rua = endereco.rua();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.bairro = endereco.bairro();
		this.cliente = cliente;
	}


	public void atualizar(EnderecoReqDto enderecoAtualizar) {
		this.cep = enderecoAtualizar.cep();
		this.cidade = enderecoAtualizar.cidade();
		this.estado = enderecoAtualizar.estado();
		this.rua = enderecoAtualizar.rua();
		this.numero = enderecoAtualizar.numero();
		this.complemento = enderecoAtualizar.complemento();
		this.bairro = enderecoAtualizar.bairro();
	}

}

