package com.example.demo.service;

public class PetEditErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PetEditErrorException(String message) {
		super(message);
	}
}
