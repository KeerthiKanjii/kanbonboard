package com.member.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler(NullUserFound.class)
	public ResponseEntity<String> nullUserFoundException(NullUserFound nufe) {
		return new ResponseEntity<>(nufe.getMessage(),HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(NullEmailFoundException.class)
	public ResponseEntity<String> nullEmailFoundException(NullEmailFoundException nufe) {
		return new ResponseEntity<>(nufe.getMessage(),HttpStatus.BAD_GATEWAY);
	}
	@ExceptionHandler(InvalidUserId.class)
	public ResponseEntity<String> invalidUserId(InvalidUserId nufe) {
		return new ResponseEntity<>(nufe.getMessage(),HttpStatus.BAD_GATEWAY);
	}
	
}
 