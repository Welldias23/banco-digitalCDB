package com.well.banco_digital_CDBW.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.repository.ClienteRepository;

@Service
public class AutheticationService implements UserDetailsService{

	private ClienteRepository clienteRepository;
	
	public AutheticationService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		return clienteRepository.findByCpf(cpf);
	}

}
