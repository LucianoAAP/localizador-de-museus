package com.betrybe.museumfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCoordinateException extends RuntimeException {

	private static final long serialVersionUID = 184603409587310457L;

	public InvalidCoordinateException(String message) {
		super(message);
	}

}
