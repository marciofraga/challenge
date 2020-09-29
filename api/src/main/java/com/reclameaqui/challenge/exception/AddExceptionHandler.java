package com.reclameaqui.challenge.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**classs that intercepts and handler exceptions */
@ControllerAdvice
public class AddExceptionHandler {
	
	/**
	 * method that handler NotFoundException
	 * 
	 * @param e - object with information about exception
	 * @param infoRequest - object with information about HTTP request
	 * @return ResponseEntity<TemplateError> - Return 404 HTTP response
	 */
    @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<TemplateError> illegalArgument(NotFoundException e, HttpServletRequest infoRequest) {
		TemplateError error = new TemplateError(HttpStatus.NOT_FOUND.value(), Instant.now().toEpochMilli(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * method that handler MethodArgumentNotValidException
	 * 
	 * @param e - object with information about exception
	 * @param request - object with information about HTTP request
	 * @return ResponseEntity<TemplateError> - Return 422 HTTP response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<TemplateError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), Instant.now().toEpochMilli(), "validation error");
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	/**
	 * method that handler HttpMessageReadableException
	 * 
	 * @param e - object with information about exception
	 * @param infoRequest - object with information about HTTP request
	 * @return ResponseEntity<TemplateError> - Return 400 HTTP response
	 */
	public ResponseEntity<TemplateError> notReadableException(HttpMessageNotReadableException e, HttpServletRequest infoRequest) {
		TemplateError error = new TemplateError(HttpStatus.BAD_REQUEST.value(), Instant.now().toEpochMilli(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
