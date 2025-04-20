package com.well.banco_digital_CDBW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.well.banco_digital_CDBW.entity.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Boolean existsByCpf(String cpf);

	UserDetails findByCpf(String cpf);

	Cliente getReferenceByCpf(String cpf);
	
	Cliente findById(Long id);

	boolean existsByEmail(String email);
	
	
}
     