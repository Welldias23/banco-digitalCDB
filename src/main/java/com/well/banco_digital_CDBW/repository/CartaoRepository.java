package com.well.banco_digital_CDBW.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	Optional<Cartao> findByIdAndContaClienteId(Long idCartao, Long idCliente);

}
