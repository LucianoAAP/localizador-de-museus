package com.betrybe.museumfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Museum not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MuseumNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -5820351358936085710L;
  
  public MuseumNotFoundException() {
    
  }
  
  public MuseumNotFoundException(String message) {
    super(message);
  }

}
