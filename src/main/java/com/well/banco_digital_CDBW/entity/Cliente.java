package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.well.banco_digital_CDBW.dto.ClienteAtualizadoDto;
import com.well.banco_digital_CDBW.dto.ClienteRequest;
import com.well.banco_digital_CDBW.service.ClienteService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Cliente")
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private BigDecimal rendaMensal;
	@Enumerated(EnumType.STRING)
	private CategoriaCliente categoria;
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	
	public Cliente(ClienteRequest clienteReq) {
		this.nome = clienteReq.nome();
		this.cpf = clienteReq.cpf();
		this.email = clienteReq.email();
		this.senha = clienteReq.senha();
		this.dataNascimento = clienteReq.dataNascimento();
		this.rendaMensal = clienteReq.rendaMensal();
		
		if (clienteReq.endereco() != null) {
			this.endereco = new Endereco(clienteReq.endereco());
					
		}
		
	}


	public void atualizarDados(ClienteAtualizadoDto clienteAtualizar) {
		if (clienteAtualizar.nome() != null) {
			this.nome = clienteAtualizar.nome();
		}
		if (clienteAtualizar.email() != null) {
			this.email = clienteAtualizar.email();
		}
		if (clienteAtualizar.senha() != null) {
			this.senha = clienteAtualizar.senha();
		}
		if (clienteAtualizar.dataNascimento() != null) {
			this.dataNascimento = clienteAtualizar.dataNascimento();
		}
		if (clienteAtualizar.rendaMensal() != null) {
			this.rendaMensal = clienteAtualizar.rendaMensal();
		}

		
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return cpf;
	}

}
