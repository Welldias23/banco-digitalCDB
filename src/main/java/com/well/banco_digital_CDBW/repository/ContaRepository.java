package com.well.banco_digital_CDBW.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.well.banco_digital_CDBW.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByIdAndClienteId(Long idConta, Long id);

	Optional<Conta> findByChavePix(String chavePix);
	
	@Query("SELECT c FROM Conta c WHERE DAY(c.dataCriacao) = :diaDoMes")
	List<Conta> findAllByDayMonth(int diaDoMes);

}
