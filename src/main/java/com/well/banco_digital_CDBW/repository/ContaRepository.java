package com.well.banco_digital_CDBW.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.well.banco_digital_CDBW.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Conta findByIdAndClienteId(Long idConta, Long id);

	Conta getReferenceByChavePix(String chavePix);

	boolean findByChavePix(String chavePix);
	
	@Query("SELECT c FROM Conta c WHERE DAY(c.dataCriacao) = :diaDoMes")
	List<Conta> findAllByDayMonth(int diaDoMes);

}
