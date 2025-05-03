package com.well.banco_digital_CDBW.service;


import com.well.banco_digital_CDBW.dto.PagamentoDto;
import com.well.banco_digital_CDBW.entity.Cliente;

public interface PagamentoStrategy {

	PagamentoDto pagar(Cliente clienteLogado, PagamentoDto pagamentoReq);
}
