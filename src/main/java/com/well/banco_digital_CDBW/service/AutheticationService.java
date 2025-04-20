package com.well.banco_digital_CDBW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.well.banco_digital_CDBW.repository.ClienteRepository;

@Service
public class AutheticationService implements UserDetailsService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		//refatorar para não usar o clliente repo
		return clienteRepository.findByCpf(cpf);
	}

}
