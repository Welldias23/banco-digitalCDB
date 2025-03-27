package com.well.banco_digital_CDBW.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TratadorGlobalDeErros extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CpfJaExistenteException.class)
	ProblemDetail tratadorCpfJaExistenteException(CpfJaExistenteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED, ex.getLocalizedMessage());
		problemDetail.setTitle("Cpf ja existe no banco de dados.");
		problemDetail.setDetail("É preciso informar um cpf não cadastrado.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(MenorDeIdadeException.class)
	ProblemDetail tratadorMenorDeIdadeException(MenorDeIdadeException ex) {
		ProblemDetail problemDatail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDatail.setTitle("O cliente é menor de 18 anos.");
		problemDatail.setDetail("É preciso ser maior de 18 anos para se cadastrar.");
		problemDatail.setProperty("TimesTemp", Instant.now());
		
		return problemDatail;
	}
	
	@ExceptionHandler(ClienteIdNaoExisteException.class)
	ProblemDetail tratadorClienteIdNaoExisteException(ClienteIdNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O id do cliente não existe no banco de dados.");
		problemDetail.setDetail("É preciso informar um id valido na url.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		return problemDetail;
	}

}
