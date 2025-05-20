package com.well.banco_digital_CDBW.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.well.banco_digital_CDBW.dto.ClienteDto;
import com.well.banco_digital_CDBW.entity.Cliente;
import com.well.banco_digital_CDBW.mapper.ClienteMapper;
import com.well.banco_digital_CDBW.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private  PasswordEncoder passwordEncoder;
	
	@Mock
	private ClienteMapper mapper;
	
	@InjectMocks
	private ClienteService clienteService;
	
	@Nested
	class cadastrarCliente {
		
		@Test
		@DisplayName("Deve criar um cliente com sucesso")
		void deveCadastrarUmClienteComSucesso() {

			// Arrange
			ClienteDto clienteDto = new ClienteDto(
				null,
				"Wellington",
				"53896527037",
				"well@gmail.com",
				"senha123",
				LocalDate.of(1995, 4, 10),
				new BigDecimal("3000")
			);

			Cliente cliente = new Cliente();
			cliente.setNome("Wellington");
			cliente.setCpf("53896527037");
			cliente.setEmail("well@gmail.com");
			cliente.setSenha("senha123");
			cliente.setDataNascimento(LocalDate.of(1995, 4, 10));
			cliente.setRendaMensal(new BigDecimal("3000"));

			ClienteDto clienteDtoResultado = new ClienteDto(
				1L,
				"Wellington",
				"53896527037",
				"well@gmail.com",
				null,
				LocalDate.of(1995, 4, 10),
				new BigDecimal("3000")
			);

			when(mapper.toCliente(clienteDto)).thenReturn(cliente);
			when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
			when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
			when(mapper.toClienteDto(any(Cliente.class))).thenReturn(clienteDtoResultado); // <-- ESSENCIAL

			// Act
			ClienteDto resultado = clienteService.cadastrarCliente(clienteDto);

			// Assert
			assertNotNull(resultado);
			assertEquals("Wellington", resultado.nome());
			verify(passwordEncoder).encode("senha123");
			verify(clienteRepository).save(any(Cliente.class));
			verify(mapper).toClienteDto(any(Cliente.class));
		}

		
	}

}
