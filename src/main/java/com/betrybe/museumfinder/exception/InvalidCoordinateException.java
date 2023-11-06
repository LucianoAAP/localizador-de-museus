package com.betrybe.museumfinder.exception;

public class InvalidCoordinateException extends Exception {

	private static final long serialVersionUID = 184603409587310457L;

	public InvalidCoordinateException(String message) {
		super(message);
	}

}
