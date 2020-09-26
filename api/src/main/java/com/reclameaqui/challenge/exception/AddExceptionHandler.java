package com.reclameaqui.challenge.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
	public ResponseEntity<TemplateError> illegalArgument(NotFoundException e, HttpServletRequest infoRequest) {
		TemplateError error = new TemplateError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<TemplateError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), System.currentTimeMillis(), "validation error");
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	public ResponseEntity<TemplateError> notReadableException(HttpMessageNotReadableException e, HttpServletRequest infoRequest) {
		TemplateError error = new TemplateError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
