package com.techreturners.moviemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.hibernate.PropertyValueException;

import java.util.Date;
import java.util.NoSuchElementException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.lang.NullPointerException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;

@RestControllerAdvice
public class MovieManagerExceptionHandler {

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage HttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorMessage SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex,
			WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { JsonParseException.class })
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ErrorMessage JsonParseException(JsonParseException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { JsonMappingException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage JsonMappingException(JsonMappingException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { InvalidFormatException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage InvalidFormatException(InvalidFormatException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ErrorMessage EmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message; 
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage noSuchElementException(NoSuchElementException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,
			WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}
	
	@ExceptionHandler(value = { PropertyValueException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorMessage PropertyValueException(PropertyValueException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}
	
	@ExceptionHandler(value = { NullPointerException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage NullPointerException(NullPointerException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return message;
	}
}
