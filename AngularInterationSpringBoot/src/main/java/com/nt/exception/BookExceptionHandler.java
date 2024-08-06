package com.nt.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionHandler {
	
	
	@ExceptionHandler(value = BookNotFoundException.class)
	public String bookNotFound(BookNotFoundException e) {
		return e.getMessage();
	}

}
