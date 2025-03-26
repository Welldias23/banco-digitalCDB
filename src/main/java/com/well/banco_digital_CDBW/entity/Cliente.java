package com.well.banco_digital_CDBW.entity;

import java.time.LocalDate;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "Cliente")
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private String senha;
	private LocalDate dataNascimento;
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	
	public Cliente(ClienteRequest clienteReq) {
		this.cpf = clienteReq.cpf();
		this.nome = clienteReq.nome();
		this.senha = clienteReq.senha();
		this.dataNascimento = clienteReq.dataNascimento();
		
		if (clienteReq.endereco() != null) {
			this.endereco = new Endereco(clienteReq.endereco());
					
		}
		
	}


	public void atualizarDados(ClienteAtualizadoDto clienteAtualizar) {
		if (clienteAtualizar.nome() != null) {
			this.nome = clienteAtualizar.nome();
		}
		if (clienteAtualizar.senha() != null) {
			this.senha = clienteAtualizar.senha();
		}
		if (clienteAtualizar.dataNascimento() != null) {
			this.dataNascimento = clienteAtualizar.dataNascimento();
		}

		
	}

}
