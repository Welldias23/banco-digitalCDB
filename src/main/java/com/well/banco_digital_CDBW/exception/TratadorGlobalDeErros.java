package com.well.banco_digital_CDBW.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
	
	@ExceptionHandler(BadCredentialsException.class)
	public ProblemDetail tratadorBadCredentialsException(BadCredentialsException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
		problemDetail.setTitle("O cpf ou senha estão incorretos.");
		problemDetail.setDetail("É preciso informar um cpf e senha validos.");
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
	
	@ExceptionHandler(CriarContaException.class)
	public ProblemDetail tratadorCriarContaException(CriarContaException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("Ouve um problema ao tentar criar uma conta.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(EnderecoIdNaoExisteException.class)
	public ProblemDetail tratadorEnderecoIdNaoExisteException(EnderecoIdNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O endereco id não existe.");
		problemDetail.setDetail("É preciso informar um enderço id valido na url.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(EmailUnicoException.class)
	public ProblemDetail tratadorEmailUnicoException(EmailUnicoException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O email ja existe.");
		problemDetail.setDetail("É preciso informar um email que seja unico.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}	
	
	@ExceptionHandler(EnderecoCadastrarException.class)
	public ProblemDetail tratadorEnderecoCadastrarException(EnderecoCadastrarException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O cliente ja tem um endereço cadastrado.");
		problemDetail.setDetail("Atualize o endereço no endpoint de atualização de endereço.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(NaotemEnderecoException.class)
	public ProblemDetail tratadorNaotemEnderecoException(NaotemEnderecoException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O cliente não tem um endereço cadastrado.");
		problemDetail.setDetail("Cadastre um endereço no endpoint de cadastro de endereço.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(ContaNaoExisteException.class)
	public ProblemDetail tratadorContaNaoExisteException(ContaNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("A conta iformada com o id não foi encontrada.");
		problemDetail.setDetail("Informe o id de uma conta valida.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(SaldoInsuficienteException.class)
	public ProblemDetail tratadorSaldoInsuficienteException(SaldoInsuficienteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O saldo em conta não é o suficienate para a transação.");
		problemDetail.setDetail("Faça um deposito em sua conta.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(ChavePixNaoExisteException.class)
	public ProblemDetail tratadorChavePixNaoExisteException(ChavePixNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("A chave pix não esta vinculada a nenhuma conta.");
		problemDetail.setDetail("Infirme uma chave pix valida.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(ChavePixJaExisteException.class)
	public ProblemDetail tratadorChavePixJaExisteException(ChavePixJaExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("A chave pix já esta vinculada a uma conta.");
		problemDetail.setDetail("Infirme uma chave pix valida.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	
	@ExceptionHandler(CategoriaNaoExisteException.class)
	public ProblemDetail tratadorCategoriaNaoExisteException(CategoriaNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O cliente nao possui uma categoria.");
		problemDetail.setDetail("Infirme o salario do cliente para ser atribuida uma categoria.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(LimiteDeCreditoInsuficiente.class)
	public ProblemDetail tratadorLimiteDeCreditoInsuficiente(LimiteDeCreditoInsuficiente ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O cartap nao possui uma limite suficiente.");
		problemDetail.setDetail("Libere limite pagando a sua fatura.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	
	@ExceptionHandler(CartaoNaoExisteException.class)
	public ProblemDetail tratadorCartaoNaoExisteException(CartaoNaoExisteException ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O id do cartão informado não existe.");
		problemDetail.setDetail("Infirme o id de um cartão valido.");
		problemDetail.setProperty("TimesTemp", Instant.now());
		
		return problemDetail;
	}
	
	@ExceptionHandler(ValorMaiorQueFatura.class)
	public ProblemDetail tratadorValorMaiorQueFatura(ValorMaiorQueFatura ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
		problemDetail.setTitle("O valor inviado é maior que o valor da fatura.");
		problemDetail.setDetail("Envie um valor igual ou menor que o da fatura.");
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