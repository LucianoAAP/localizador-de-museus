package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;

/**
 * Interface for Museum service class.
 */
public interface MuseumServiceInterface {

  Museum getClosestMuseum(Coordinate coordinate, Double maxDistance)
  		throws InvalidCoordinateException, MuseumNotFoundException;

  Museum createMuseum(Museum museum) throws InvalidCoordinateException;

  Museum getMuseum(Long id);
}
