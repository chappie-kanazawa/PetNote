package com.example.demo.service;

public class WrongPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message) {
		super(message);
	}
}
