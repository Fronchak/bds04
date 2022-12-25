package com.devsuperior.bds04.exceptions.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.devsuperior.bds04.exceptions.ValidationExceptionResponse;

@RestControllerAdvice
public class CustomizeResponseEntityExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationExceptionResponse> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException e, WebRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationExceptionResponse response = new ValidationExceptionResponse();
		response.setTimestamp(Instant.now());
		response.setStatus(status.value());
		response.setError("Validation Error");
		response.setMessage(e.getMessage());
		response.setPath(request.getDescription(false));

		for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			response.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(response);
	}
	 
}
