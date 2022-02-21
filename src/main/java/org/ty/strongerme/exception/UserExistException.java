package org.ty.strongerme.exception;

public class UserExistException extends RuntimeException {
	
	public UserExistException() {
		super("user doesn't have token");
	}
	public UserExistException(String msg) {
		super(msg);
	}
}
