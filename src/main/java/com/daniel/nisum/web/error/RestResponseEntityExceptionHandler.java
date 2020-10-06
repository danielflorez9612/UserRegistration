package com.daniel.nisum.web.error;


import javax.validation.ConstraintDefinitionException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.daniel.nisum.web.dto.ErrorDto;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getField() + " "
				+ ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		ErrorDto errorDto = new ErrorDto(errorMessage);
		return new ResponseEntity<>(errorDto, status);
	}
	

	@ExceptionHandler(ConstraintDefinitionException.class)
	protected ResponseEntity<Object> handleConstraintDefinitionException(ConstraintDefinitionException ex) {
		ErrorDto errorDto = new ErrorDto(ex.getMessage());
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(UserAlreadyExistException.class)
	protected ResponseEntity<Object> handleUserAlreadyExistException() {
		ErrorDto errorDto = new ErrorDto("El correo ya está registrado");
		return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
	}

}
