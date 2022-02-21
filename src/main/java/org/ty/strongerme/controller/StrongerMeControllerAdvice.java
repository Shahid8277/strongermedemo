package org.ty.strongerme.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ty.strongerme.exception.ReferenceNotFound;
import org.ty.strongerme.exception.UserExistException;

@RestControllerAdvice
public class StrongerMeControllerAdvice {
	
	@ExceptionHandler(ReferenceNotFound.class)
	public ResponseEntity<?> refNotFound(ReferenceNotFound notfound) {
		return new ResponseEntity<String>(notfound.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<?> existUSer(UserExistException userExist) {
		return new ResponseEntity<String>(userExist.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
