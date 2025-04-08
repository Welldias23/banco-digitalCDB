package com.well.banco_digital_CDBW.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.well.banco_digital_CDBW.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	Conta findByIdAndClienteId(Long idConta, Long id);

	Conta getReferenceByChavePix(String chavePix);

	boolean findByChavePix(String chavePix);

	@Query("SELECT * FROM Conta EXTRACT(DAY FROM created_at) = :diaDoMes AND created_at >= date_trunc('month', CURRENT_DATE - INTERVAL '1 month')  AND created_at < date_trunc('month', CURRENT_DATE)")
	List<Conta> findAllByDayMoth(int diaDoMes);

}
