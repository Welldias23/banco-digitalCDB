package com.well.banco_digital_CDBW.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.CartaoDebito;

public interface CartaoDebitoRepository extends JpaRepository<CartaoDebito, Long>{

	Optional<CartaoDebito> findByIdAndContaClienteId(Long idCartao, Long id);

}
