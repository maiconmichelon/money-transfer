package com.michelon.exception;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = -4152134988599072717L;

	public AccountNotFoundException(String message) {
		super(message);
	}
	
}
