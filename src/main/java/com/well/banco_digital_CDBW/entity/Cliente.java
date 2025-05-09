package com.well.banco_digital_CDBW.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.well.banco_digital_CDBW.dto.ClienteDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Endereco endereco;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Conta> contas;
	
	
	public Cliente(Cliente clienteReq) {
		this.nome = clienteReq.getNome();
		this.cpf = clienteReq.getCpf();
		this.email = clienteReq.getEmail();
		this.senha = clienteReq.getSenha();
		this.dataNascimento = clienteReq.getDataNascimento();
		this.rendaMensal = clienteReq.getRendaMensal();
		
	}


	public void atualizarCliente(Cliente clienteReq) {
		Optional.ofNullable(clienteReq.getNome())
					.ifPresent(nome -> this.nome = nome);
		Optional.ofNullable(clienteReq.getEmail())
					.ifPresent(email -> this.email = email);
		Optional.ofNullable(clienteReq.getSenha())
					.ifPresent(senha -> this.senha = senha);
		Optional.ofNullable(clienteReq.getDataNascimento())
					.ifPresent(dataNascimento -> this.dataNascimento = dataNascimento);
		Optional.ofNullable(clienteReq.getRendaMensal())
					.ifPresent(rendaMensal -> this.rendaMensal = rendaMensal);
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


	public void definirCategoria(ClienteDto clienteReq) {
		this.categoria = Optional.ofNullable(clienteReq.rendaMensal())
				.map(r -> r.compareTo(new BigDecimal("1512")) <= 0 ? CategoriaCliente.COMUM
						: r.compareTo(new BigDecimal("3000")) <= 0 ? CategoriaCliente.PREMIUM
						: CategoriaCliente.SUPER).orElse(categoria);
	}


	public Cliente(int i, String nome, String cpf, String email, String senha, LocalDate dataNascimento, BigDecimal rendaMensal) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.rendaMensal = rendaMensal;
	}

}
