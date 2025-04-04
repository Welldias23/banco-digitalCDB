package com.well.banco_digital_CDBW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Conta findByIdAndClienteId(Long idConta, Long id);

}
