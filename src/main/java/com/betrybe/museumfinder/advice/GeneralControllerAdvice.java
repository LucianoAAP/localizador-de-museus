package com.betrybe.museumfinder.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;

/**
 * Controller advice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {
  /**
   * Invalid coordinate exception handler.
   */
  @ExceptionHandler({InvalidCoordinateException.class})
  public ResponseEntity<String> handleInvalidCoordinateException(InvalidCoordinateException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coordenada inválida!");
  }
  
  /**
   * Museum not found exception handler.
   */
  @ExceptionHandler({MuseumNotFoundException.class})
  public ResponseEntity<String> handleMuseumNotFoundException(MuseumNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Museu não encontrado!");
  }
  
  /**
   * General exception handler.
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleException(Exception exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno!");
  }
}
