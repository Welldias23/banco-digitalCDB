package com.well.banco_digital_CDBW.service;


import com.well.banco_digital_CDBW.dto.PagamentoReqDto;
import com.well.banco_digital_CDBW.dto.PagamentoResDto;
import com.well.banco_digital_CDBW.entity.Cliente;

public interface PagamentoStrategy {

	PagamentoResDto pagar(Long idCartao, Cliente clienteLogado, PagamentoReqDto pagamentoReq);
}
