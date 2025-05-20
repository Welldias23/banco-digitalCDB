package com.well.banco_digital_CDBW.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.Pagamento;
import com.well.banco_digital_CDBW.entity.PagamentoDebito;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

	List<PagamentoDebito> findAllById(Long id);

}
