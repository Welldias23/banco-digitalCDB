package com.well.banco_digital_CDBW.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TratadorGlobalDeErros extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CpfUnicoException.class)
	public ProblemDetail tratadorCpfUnicoException(CpfUnicoException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.ALREADY_REPORTED, ex.getLocalizedMessage());
		problemDetail.setTitle("O cpf ja existe no banco de dados.");
		problemDetail.setDetail("É preciso informar um cpf não cadastrado.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(MenorDeIdadeException.class)
	public ProblemDetail tratadorMenorDeIdadeException(MenorDeIdadeException ex) {
		ProblemDetail problemDatail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDatail.setTitle("O cliente é menor de 18 anos.");
		problemDatail.setDetail("É preciso ser maior de 18 anos para se cadastrar.");
		problemDatail.setProperty("TimesTemp", Instant.now());
		
		return problemDatail;
	}
	
	@ExceptionHandler(ClienteIdNaoExisteException.class)
	public ProblemDetail tratadorClienteIdNaoExisteException(ClienteIdNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		problemDetail.setTitle("O id do cliente não existe no banco de dados.");
		problemDetail.setDetail("É preciso informar um id valido na url.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(TokenInvalidoExcepition.class)
	public ProblemDetail tratadorTokenInvalido(TokenInvalidoExcepition ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O Token é invalido.");
		problemDetail.setDetail("É preciso estar logado para acessar essa url.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers, 
			HttpStatusCode status, 
			WebRequest request) {
		
		String errosDeValidacao = ex.getBindingResult().getFieldErrors().stream()
			.map(error -> error.getField() + ": " + error.getDefaultMessage())
			.collect(Collectors.joining("; "));
		
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("Erro de Validação");
		problemDetail.setDetail(errosDeValidacao);
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return handleExceptionInternal(ex, problemDetail, headers, HttpStatus.BAD_REQUEST, request);
	}
}