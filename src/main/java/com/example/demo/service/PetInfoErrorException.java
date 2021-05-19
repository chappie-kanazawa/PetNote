package com.example.demo.service;

public class PetInfoErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PetInfoErrorException(String message) {
		super(message);
	}
}
