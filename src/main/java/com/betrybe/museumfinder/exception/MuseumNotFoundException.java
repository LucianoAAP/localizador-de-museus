package com.betrybe.museumfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MuseumNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5820351358936085710L;

	public MuseumNotFoundException(String message) {
		super(message);
	}

}
