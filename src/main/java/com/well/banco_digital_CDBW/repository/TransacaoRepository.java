package com.well.banco_digital_CDBW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.well.banco_digital_CDBW.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
