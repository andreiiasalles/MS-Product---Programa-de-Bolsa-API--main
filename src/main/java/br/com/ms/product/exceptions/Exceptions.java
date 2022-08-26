package br.com.ms.product.exceptions;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class Exceptions {
	@ExceptionHandler(value = { NoSuchElementException.class, EmptyResultDataAccessException.class,
			EntityNotFoundException.class })
	public ResponseEntity<ExceptionConfig> NotFound(HttpServletRequest code404) {
		ExceptionConfig codee404 = new ExceptionConfig(HttpStatus.NOT_FOUND.value(), "Not Found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(codee404);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class, HttpMessageNotReadableException.class,
			HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class })
	public ResponseEntity<ExceptionConfig> BadRequest(HttpServletRequest code400) {
		ExceptionConfig codee400 = new ExceptionConfig(HttpStatus.BAD_REQUEST.value(), "Bad Request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(codee400);
	}

	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ExceptionConfig> globalExceptionHandler (Exception ex, WebRequest request){
		ExceptionConfig menssage = new ExceptionConfig(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ExceptionConfig>(menssage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
