package com.betrybe.museumfinder.exception;

public class MuseumNotFoundException extends Exception {

	private static final long serialVersionUID = -5820351358936085710L;

	public MuseumNotFoundException(String message) {
		super(message);
	}

}
