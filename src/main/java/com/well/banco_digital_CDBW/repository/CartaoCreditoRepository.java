package com.well.banco_digital_CDBW.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.Cartao;
import com.well.banco_digital_CDBW.entity.CartaoCredito;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long>{

	Optional<CartaoCredito> findByIdAndContaClienteId(Long idCartao, Long id);

	List<Cartao> findByContaId(Long id);

}
